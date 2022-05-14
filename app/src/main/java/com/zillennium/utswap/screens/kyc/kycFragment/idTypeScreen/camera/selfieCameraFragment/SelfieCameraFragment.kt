package com.zillennium.utswap.screens.kyc.idTypeScreen.camera.selfieCameraActivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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
import androidx.core.net.toFile
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.google.common.util.concurrent.ListenableFuture
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycCameraSelfieBinding
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.selfieCameraFragment.SelfieCameraPresenter
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.selfieCameraFragment.SelfieCameraView
import com.zillennium.utswap.utils.FileCreator
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SelfieCameraFragment :
    BaseMvpFragment<SelfieCameraView.View, SelfieCameraView.Presenter, FragmentKycCameraSelfieBinding>(),
    SelfieCameraView.View {

    override var mPresenter: SelfieCameraView.Presenter = SelfieCameraPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_camera_selfie

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

                if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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
                    findNavController().popBackStack()
                }

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun setupCamera() {
        providerListenableFuture = ProcessCameraProvider.getInstance(requireActivity())
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
        }, ContextCompat.getMainExecutor(requireActivity()))
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    private fun buildPreview(): UseCase? {

//        int displays = binding.viewFinder.getDisplay().getRotation();
        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
        val preview = Preview.Builder()
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .build()
        preview.setSurfaceProvider(mPreviewView!!.surfaceProvider)
        return preview
    }

    private fun buildImageCapture(): ImageCapture? {

//        Display displays = binding.viewFinder.getDisplay();
        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
        val builder = ImageCapture.Builder()
        val imageCapture = builder //                .setTargetRotation(displays.getRotation())
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
            .build()
        val executor: Executor = Executors.newSingleThreadExecutor()
        btnTakePhoto = binding.btnTakePhoto
        btnTakePhoto!!.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            btnTakePhoto!!.isClickable = false
//            val timestamp = System.currentTimeMillis()
//            val contentValues = ContentValues()
//            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp)
//            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//            val metadata = ImageCapture.Metadata()
//            metadata.isReversedHorizontal = true
//            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
//                contentResolver,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                contentValues
//            ).setMetadata(metadata).build()
            val photoFile = FileCreator.createTempImageFile()
            photoFile.apply {
                val outputOptions =
                    this?.let { it1 -> ImageCapture.OutputFileOptions.Builder(it1).build() }
                if (outputOptions != null) {
                    imageCapture.takePicture(
                        outputOptions,
                        executor,
                        object : ImageCapture.OnImageSavedCallback{
                            override fun onError(exception: ImageCaptureException) {
                            }

                            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
                                val bitmap = BitmapFactory.decodeFile(savedUri.path)
                                val rotatedBitmap =
                                    FileCreator.rotate(bitmap, savedUri.toFile().absolutePath ?: "")
                                photoFile?.delete()
                                binding.apply {
                                    val croppedImage =
                                        cropImage(rotatedBitmap, viewFinder,rectangles)
                                    val path = FileCreator.saveImage(croppedImage)
                                    requireActivity().runOnUiThread{
                                        progressBar.visibility = View.GONE
                                        btnTakePhoto.isClickable = true
                                        try {
                                            KYCPreferences().SELFIE_HOLDING = path
                                            findNavController().popBackStack()
                                        }catch (e: Exception){
                                            KYCPreferences().removeValue("SELFIE_HOLDING")
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
//            imageCapture.takePicture(
//                outputFileOptions,
//                executor,
//                object : ImageCapture.OnImageSavedCallback {
//                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                        runOnUiThread {
//                            @SuppressLint("SimpleDateFormat") val timeStamp =
//                                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//                            val imageFileName = "JPEG_" + timeStamp + "_"
//                            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//                            try {
//                                val image = File.createTempFile(
//                                    imageFileName,  /* prefix */
//                                    ".jpg",  /* suffix */
//                                    storageDir /* directory */
//                                )
//                                if (image.exists()) {
//                                    val saveUri = outputFileResults.savedUri
//                                    val imageCamera = binding.imageCamera
//                                    imageCamera.setImageURI(saveUri)
//                                    imageCamera.visibility = View.VISIBLE
//                                    image.delete()
//                                }
//                            } catch (e: IOException) {
//                                e.printStackTrace()
//                            }
//                        }
//                    }
//
//                    override fun onError(error: ImageCaptureException) {
//                        error.printStackTrace()
//                        //                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                })
        }
        return imageCapture
    }

    private fun buildAnalysis(): UseCase? {
//        Display displays = binding.viewFinder.getDisplay();
        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
        return ImageAnalysis.Builder() //                .setTargetRotation(displays.getRotation())
            .setTargetResolution(Size(metrics.widthPixels, metrics.heightPixels))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
            .setImageQueueDepth(10)
            .build()
    }

    private fun cropImage(bitmap: Bitmap, frame: View, reference: View): ByteArray {
        val heightOriginal = frame.height
        val widthOriginal = frame.width
        val heightFrame = reference.height
        val widthFrame = reference.width
        val leftFrame = reference.left
        val topFrame = reference.top
        val heightReal = bitmap.height
        val widthReal = bitmap.width
        val widthFinal = widthFrame * widthReal / widthOriginal
        val heightFinal = heightFrame * heightReal / heightOriginal
        val leftFinal = leftFrame * widthReal / widthOriginal
        val topFinal = topFrame * heightReal / heightOriginal
        val bitmapFinal = Bitmap.createBitmap(
            bitmap,
            leftFinal, topFinal, widthFinal, heightFinal
        )
        return FileCreator.bitMapToBytesArray(bitmapFinal)
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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
                Toast.makeText(requireActivity(), "camera permission granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireActivity(), "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

}
