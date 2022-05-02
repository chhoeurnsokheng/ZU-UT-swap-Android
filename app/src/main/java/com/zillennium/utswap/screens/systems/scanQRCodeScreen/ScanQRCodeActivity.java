package com.zillennium.utswap.screens.systems.scanQRCodeScreen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Size;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySystemScanQrcodeBinding;

import java.util.concurrent.ExecutionException;

public class ScanQRCodeActivity extends AppCompatActivity {

    ActivitySystemScanQrcodeBinding binding;

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

        binding = ActivitySystemScanQrcodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPreviewView = binding.viewFinder;

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        setupCamera();

//        if (classheckSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
//        }
        
        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(ScanQRCodeActivity.this, ivBack);
    }

    private void setupCamera() {
        providerListenableFuture = processCameraProvider.getInstance(this);
        providerListenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = providerListenableFuture.get();
                Camera camera = cameraProvider.bindToLifecycle(
                        (LifecycleOwner)this,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        buildPreview());
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
