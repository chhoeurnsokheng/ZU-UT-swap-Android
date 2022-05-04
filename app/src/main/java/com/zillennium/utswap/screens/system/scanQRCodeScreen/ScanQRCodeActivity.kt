package com.zillennium.utswap.screens.system.scanQRCodeScreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.DisplayMetrics
import android.util.Size
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemScanQrcodeBinding
import java.io.IOException
import java.util.concurrent.ExecutionException

class ScanQRCodeActivity :
    BaseMvpActivity<ScanQRCodeView.View, ScanQRCodeView.Presenter, ActivitySystemScanQrcodeBinding>(),
    ScanQRCodeView.View {

    override var mPresenter: ScanQRCodeView.Presenter = ScanQRCodePresenter()
    override val layoutResource: Int = R.layout.activity_system_scan_qrcode

    var mPreviewView: PreviewView? = null
    var imageCapture: ImageView? = null
    var btnTakePhoto: CardView? = null

    private var providerListenableFuture: ListenableFuture<ProcessCameraProvider>? = null
    private val processCameraProvider: ProcessCameraProvider? = null

    private val MY_CAMERA_REQUEST_CODE = 100

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                mPreviewView = binding.viewFinder

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_REQUEST_CODE
                    )
                }

                setupCamera()

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun setupCamera() {
        providerListenableFuture = ProcessCameraProvider.getInstance(UTSwapApp.instance)
        providerListenableFuture!!.addListener(Runnable {
            try {
                val cameraProvider = providerListenableFuture!!.get()
                val camera = cameraProvider.bindToLifecycle(
                    (this as LifecycleOwner),
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    buildPreview()
                )
            } catch (e: ExecutionException) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            } catch (e: InterruptedException) {
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    private fun buildPreview(): UseCase? {

//        int displays = binding.viewFinder.getDisplay().getRotation();
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val preview = Preview.Builder()
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .build()
        preview.setSurfaceProvider(mPreviewView!!.surfaceProvider)
        return preview
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

}