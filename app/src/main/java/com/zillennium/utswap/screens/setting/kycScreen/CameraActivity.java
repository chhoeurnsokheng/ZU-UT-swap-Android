package com.zillennium.utswap.screens.setting.kycScreen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivityKycCameraBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {

    ActivityKycCameraBinding binding;

    PreviewView mPreviewView;
    ImageView imageCapture;
    CardView btnTakePhoto;


    private ListenableFuture<ProcessCameraProvider> providerListenableFuture;
    private ProcessCameraProvider processCameraProvider;

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityKycCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPreviewView = binding.viewFinder;

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extras.getInt("sample");

            int sample = extras.getInt("sample");
            setImage(sample);

            //The key argument here must match that used in the other activity
        }

        setupCamera();



        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(CameraActivity.this, ivBack);
    }

    private void setupCamera() {
        providerListenableFuture = processCameraProvider.getInstance(this);
        providerListenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = providerListenableFuture.get();
                Camera camera = cameraProvider.bindToLifecycle(
                        (LifecycleOwner)this,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        buildPreview(),
                        buildImageCapture(),
                        buildAnalysis());
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private UseCase buildPreview() throws ExecutionException, InterruptedException {

//        int displays = binding.viewFinder.getDisplay().getRotation();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Preview preview = new Preview.Builder()
                .setTargetResolution(new Size(metrics.widthPixels, metrics.heightPixels ))
                .build();
        preview.setSurfaceProvider(mPreviewView.getSurfaceProvider());


        return preview;


    }

    private UseCase buildImageCapture(){

//        Display displays = binding.viewFinder.getDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ImageCapture.Builder builder = new ImageCapture.Builder();
        final ImageCapture imageCapture = builder
//                .setTargetRotation(displays.getRotation())
                .setTargetResolution(new Size(metrics.widthPixels, metrics.heightPixels))
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build();

        Executor executor = Executors.newSingleThreadExecutor();
        btnTakePhoto = binding.btnTakePhoto;

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                long timestamp = System.currentTimeMillis();

                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

                ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(
                        getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                ).build();
                imageCapture.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback () {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                String imageFileName = "JPEG_" + timeStamp + "_";
                                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                                try {
                                    File image = File.createTempFile(
                                            imageFileName, /* prefix */
                                            ".jpg", /* suffix */
                                            storageDir      /* directory */
                                    );
                                    if (image.exists()) {


                                        Uri saveUri = outputFileResults.getSavedUri();

//                                        Bitmap bitmap = BitmapFactory.decodeFile(saveUri.getPath());



//                                        Bitmap croppedImage = cropImage(bitmap, mPreviewView, binding.frameImage);

                                        ImageView imageCamera = binding.imageCamera;
                                        imageCamera.setImageURI(saveUri);
                                        imageCamera.setVisibility(View.VISIBLE);


                                        image.delete();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException error) {
                        error.printStackTrace();
//                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return imageCapture;
    }

    private UseCase buildAnalysis(){
//        Display displays = binding.viewFinder.getDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
//                .setTargetRotation(displays.getRotation())
                .setTargetResolution(new Size(metrics.widthPixels, metrics.heightPixels))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
                .setImageQueueDepth(10)
                .build();

        return imageAnalysis;
    }

    private Bitmap cropImage(Bitmap bitmap, View frame, View reference){
        float heightOriginal = frame.getHeight();
        float widthOriginal = frame.getWidth();
        float heightFrame = reference.getHeight();
        float widthFrame = reference.getWidth();
        float leftFrame = reference.getLeft();
        float topFrame = reference.getTop();
        int heightReal = bitmap.getHeight();
        int widthReal = bitmap.getWidth();
        int widthFinal = (int) (widthFrame * widthReal / widthOriginal);
        int heightFinal = (int) (heightFrame * heightReal / heightOriginal);
        int leftFinal = (int) (leftFrame * widthReal / widthOriginal);
        int topFinal = (int) (topFrame * heightReal / heightOriginal);
        Bitmap bitmapFinal = Bitmap.createBitmap(bitmap, leftFinal, topFinal, widthFinal, heightFinal);
        return bitmapFinal;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setImage(int resource){
        ImageView ivSample = binding.ivSample;
        ivSample.setImageResource(resource);
    }
}
