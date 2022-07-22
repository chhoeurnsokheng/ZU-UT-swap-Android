package com.zillennium.utswap.module.account.accountScreen.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountChangeProfileCameraBinding
import com.zillennium.utswap.utils.FileCreator
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class ChangeProfileCameraActivity  :
    BaseMvpActivity<ChangeProfileCameraView.View, ChangeProfileCameraView.Presenter, ActivityAccountChangeProfileCameraBinding>(),
    ChangeProfileCameraView.View {

    override var mPresenter: ChangeProfileCameraView.Presenter = ChangeProfileCameraPresenter()
    override val layoutResource: Int = R.layout.activity_account_change_profile_camera

    var mPreviewView: PreviewView? = null
    var btnTakePhoto: CardView? = null
    private val MY_CAMERA_REQUEST_CODE = 100
    private var providerListenableFuture: ListenableFuture<ProcessCameraProvider>? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        super.initView()

        toolBar()

        try {
            binding.apply {
//                ivBack.setOnClickListener{
//                    finish()
//                }

                viewFinder.minimumHeight = viewFinder.width
                imgPhotoTest.minimumHeight = viewFinder.width

                if (ContextCompat.checkSelfPermission(this@ChangeProfileCameraActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_REQUEST_CODE
                    )
                }

                mPreviewView = binding.viewFinder
                setupCamera()
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
    private fun setupCamera() {
        providerListenableFuture = ProcessCameraProvider.getInstance(UTSwapApp.instance)
        providerListenableFuture?.addListener({
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
            } catch (e: InterruptedException) {
            }
        }, ContextCompat.getMainExecutor(UTSwapApp.instance))
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    private fun buildPreview(): UseCase {

//        int displays = binding.viewFinder.getDisplay().getRotation();
        val metrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(metrics)
        val preview = Preview.Builder()
            .setTargetResolution(Size(metrics.widthPixels, metrics.widthPixels))
            .build()
        preview.setSurfaceProvider(mPreviewView!!.surfaceProvider)
        return preview
    }

    private fun buildImageCapture(): ImageCapture {

        val metrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(metrics)
        val builder = ImageCapture.Builder()
        val imageCapture = builder
            .setTargetResolution(Size(metrics.widthPixels, metrics.widthPixels))
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
            .build()
        val executor: Executor = Executors.newSingleThreadExecutor()
        btnTakePhoto = binding.btnTakePhoto

        btnTakePhoto!!.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnTakePhoto.isClickable = false

            val metadata = ImageCapture.Metadata()
            metadata.isReversedHorizontal = true
            val photoFile = FileCreator.createTempImageFile()
            photoFile.apply {
                val outputOptions =
                    this?.let { it1 -> ImageCapture.OutputFileOptions.Builder(it1).setMetadata(metadata).build() }
                if (outputOptions != null) {
                    imageCapture.takePicture(
                        outputOptions,
                        executor,
                        object : ImageCapture.OnImageSavedCallback{
                            override fun onError(exception: ImageCaptureException) {
                            }

                            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                var savedUri = output.savedUri ?: Uri.fromFile(photoFile)

                                var bitmap = BitmapFactory.decodeFile(savedUri?.path)

                                val width = bitmap.width
                                val height = bitmap.height

                                // This will not scale but will flip on the X axis.
                                val mtx = Matrix()
                                mtx.preScale(-1f, 1f)

                                // Create a Bitmap with the flip matrix applied to it.
                                var reflection = Bitmap.createBitmap(bitmap, 0, 0, width, height, mtx, false)

                                val rotatedBitmap = FileCreator.rotate(reflection, savedUri!!.toFile().absolutePath ?: "")
                                photoFile?.delete()
                                binding.apply {

                                    this@ChangeProfileCameraActivity.runOnUiThread{
                                        binding.progressBar.visibility = View.GONE
                                        btnTakePhoto.isClickable = true
                                        imgPhotoTest.setImageBitmap(rotatedBitmap)
                                        try {

                                        }catch (e: Exception){

                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
        return imageCapture
    }

    private fun buildAnalysis(): UseCase {

        val metrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(metrics)
        return ImageAnalysis.Builder()
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
            .setImageQueueDepth(10)
            .build()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(UTSwapApp.instance, "camera permission granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(UTSwapApp.instance, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}