package com.zillennium.utswap.screens.kyc.idTypeScreen.camera.selfieCameraActivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycCameraSelfieBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SelfieCameraActivity :
    BaseMvpActivity<SelfieCameraView.View, SelfieCameraView.Presenter, ActivityKycCameraSelfieBinding>(),
    SelfieCameraView.View {

    override var mPresenter: SelfieCameraView.Presenter = SelfieCameraPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_camera_selfie

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

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_REQUEST_CODE
                    )
                }

                mPreviewView = binding.viewFinder
                setupCamera()

                // Set Passed Back

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
        providerListenableFuture = ProcessCameraProvider.getInstance(this)
        providerListenableFuture!!.addListener({
            try {
                val cameraProvider = providerListenableFuture!!.get()
                val camera = cameraProvider.bindToLifecycle(
                    (this as LifecycleOwner),
                    CameraSelector.DEFAULT_FRONT_CAMERA,
                    buildPreview(),
                    buildImageCapture(),
                    buildAnalysis()
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

    private fun buildImageCapture(): UseCase? {

//        Display displays = binding.viewFinder.getDisplay();
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val builder = ImageCapture.Builder()
        val imageCapture = builder //                .setTargetRotation(displays.getRotation())
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
            .build()
        val executor: Executor = Executors.newSingleThreadExecutor()
        btnTakePhoto = binding.btnTakePhoto
        btnTakePhoto!!.setOnClickListener {
            val timestamp = System.currentTimeMillis()
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp)
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            val metadata = ImageCapture.Metadata()
            metadata.isReversedHorizontal = true
            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
                contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ).setMetadata(metadata).build()
            imageCapture.takePicture(
                outputFileOptions,
                executor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        runOnUiThread {
                            @SuppressLint("SimpleDateFormat") val timeStamp =
                                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                            val imageFileName = "JPEG_" + timeStamp + "_"
                            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            try {
                                val image = File.createTempFile(
                                    imageFileName,  /* prefix */
                                    ".jpg",  /* suffix */
                                    storageDir /* directory */
                                )
                                if (image.exists()) {
                                    val saveUri = outputFileResults.savedUri
                                    val imageCamera = binding.imageCamera
                                    imageCamera.setImageURI(saveUri)
                                    imageCamera.visibility = View.VISIBLE
                                    image.delete()
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    override fun onError(error: ImageCaptureException) {
                        error.printStackTrace()
                        //                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        }
        return imageCapture
    }

    private fun buildAnalysis(): UseCase? {
//        Display displays = binding.viewFinder.getDisplay();
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        return ImageAnalysis.Builder() //                .setTargetRotation(displays.getRotation())
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
            .setImageQueueDepth(10)
            .build()
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        val mCurrentPhotoPath = image.absolutePath
        return image
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