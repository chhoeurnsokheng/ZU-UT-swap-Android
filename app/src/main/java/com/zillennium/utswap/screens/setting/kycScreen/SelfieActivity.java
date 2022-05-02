package com.zillennium.utswap.screens.setting.kycScreen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.PackageManager;
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
import com.zillennium.utswap.databinding.ActivityKycSelfieBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SelfieActivity extends AppCompatActivity {

    ActivityKycSelfieBinding binding;

    PreviewView mPreviewView;
    CardView btnTakePhoto;


    private ListenableFuture<ProcessCameraProvider> providerListenableFuture;
    private ProcessCameraProvider processCameraProvider;

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityKycSelfieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        mPreviewView = binding.viewFinder;
        setupCamera();

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(SelfieActivity.this, ivBack);
    }

    private void setupCamera() {
        providerListenableFuture = processCameraProvider.getInstance(this);
        providerListenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = providerListenableFuture.get();
                Camera camera = cameraProvider.bindToLifecycle(
                        (LifecycleOwner)this,
                        CameraSelector.DEFAULT_FRONT_CAMERA,
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

                ImageCapture.Metadata metadata = new ImageCapture.Metadata();
                metadata.setReversedHorizontal(true);
                ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(
                        getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                ).setMetadata(metadata).build();

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

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        );
        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
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
}
