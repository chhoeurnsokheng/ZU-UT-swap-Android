package com.zillennium.utswap.screens.kyc.idTypeScreen.camera.idCardCameraActivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
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
import com.zillennium.utswap.bases.BasePassedActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycCameraIdCardBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class IDCardCameraActivity :
    BaseMvpActivity<IDCardCameraView.View, IDCardCameraView.Presenter, ActivityKycCameraIdCardBinding>(),
    IDCardCameraView.View {

    override var mPresenter: IDCardCameraView.Presenter = IDCardCameraPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_camera_id_card

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

                mPreviewView = viewFinder

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_REQUEST_CODE
                    )
                }

                val extras = intent.extras
                if (extras != null) {
                    extras.getInt("sample")
                    val sample = extras.getInt("sample")
                    setImage(sample)

                    //The key argument here must match that used in the other activity
                }

                setupCamera()


                // Set Passed Back


                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }

        } catch (error: IOException) {
            // Must be safe
        }
    }

    private fun setupCamera() {
        val providerListenableFuture = ProcessCameraProvider.getInstance(this)
        providerListenableFuture.addListener(Runnable {
            try {
                val cameraProvider = providerListenableFuture.get()
                val camera = cameraProvider.bindToLifecycle(
                    (this as LifecycleOwner),
                    CameraSelector.DEFAULT_BACK_CAMERA,
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
            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
                contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ).build()
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

                                    //                                        Bitmap bitmap = BitmapFactory.decodeFile(saveUri.getPath());


                                    //                                        Bitmap croppedImage = cropImage(bitmap, mPreviewView, binding.frameImage);
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

    private fun cropImage(
        bitmap: Bitmap,
        frame: View,
        reference: View
    ): Bitmap? {
        val heightOriginal = frame.height.toFloat()
        val widthOriginal = frame.width.toFloat()
        val heightFrame = reference.height.toFloat()
        val widthFrame = reference.width.toFloat()
        val leftFrame = reference.left.toFloat()
        val topFrame = reference.top.toFloat()
        val heightReal = bitmap.height
        val widthReal = bitmap.width
        val widthFinal = (widthFrame * widthReal / widthOriginal).toInt()
        val heightFinal = (heightFrame * heightReal / heightOriginal).toInt()
        val leftFinal = (leftFrame * widthReal / widthOriginal).toInt()
        val topFinal = (topFrame * heightReal / heightOriginal).toInt()
        return Bitmap.createBitmap(bitmap, leftFinal, topFinal, widthFinal, heightFinal)
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

    private fun setImage(resource: Int) {
        binding.apply {
            ivSample.setImageResource(resource)
        }
    }
}