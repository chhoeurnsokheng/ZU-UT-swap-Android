package com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.DisplayMetrics
import android.util.Log
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
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.google.common.util.concurrent.ListenableFuture
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycCameraIdCardBinding
import com.zillennium.utswap.utils.FileCreator
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class IDCardCameraFragment :
    BaseMvpFragment<IDCardCameraView.View, IDCardCameraView.Presenter, FragmentKycCameraIdCardBinding>(),
    IDCardCameraView.View {

    override var mPresenter: IDCardCameraView.Presenter = IDCardCameraPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_camera_id_card

    var mPreviewView: PreviewView? = null
    var imageCapture: ImageView? = null
    var btnTakePhoto: CardView? = null


    private var providerListenableFuture: ListenableFuture<ProcessCameraProvider>? = null
    private val processCameraProvider: ProcessCameraProvider? = null

    private val MY_CAMERA_REQUEST_CODE = 100
    private var statusNumber : Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        super.initView()
        try {
            binding.apply {



                ivBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_REQUEST_CODE
                    )
                }

                when (arguments?.getString("title").toString()){
                    "id_card_front" -> { ivSample.setImageResource(R.drawable.ic_national_id_front) }
                    "id_card_back" -> { ivSample.setImageResource(R.drawable.ic_national_id_back) }
                    "passport_front" -> { ivSample.setImageResource(R.drawable.ic_passport_front) }
                }

                mPreviewView = viewFinder
                setupCamera()

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun setupCamera() {
        val providerListenableFuture = ProcessCameraProvider.getInstance(requireActivity())
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
            btnTakePhoto!!.isClickable = false
            binding.progressBar.visibility = View.VISIBLE
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
                                        cropImage(rotatedBitmap, viewFinder, frameImage)
                                    val path = FileCreator.saveImage(croppedImage)
                                    requireActivity().runOnUiThread{
                                        progressBar.visibility = View.GONE
                                        btnTakePhoto.isClickable = true
                                        try {
                                            when (arguments?.getString("title").toString()){
                                                "id_card_front" -> {
                                                    KYCPreferences().NATIONAL_ID_FRONT = path
                                                }
                                                "id_card_back" -> {
                                                    KYCPreferences().NATIONAL_ID_BACK = path
                                                }
                                                "passport_front" -> {
                                                    KYCPreferences().PASSPORT_FRONT = path
                                                }
                                            }

                                            findNavController().popBackStack()
                                        }catch (e: Exception){
                                            when (arguments?.getString("title").toString()){
                                                "id_card_front" -> {
                                                    KYCPreferences().removeValue("NATIONAL_ID_FRONT")
                                                }
                                                "id_card_back" -> {
                                                    KYCPreferences().removeValue("NATIONAL_ID_BACK")
                                                }
                                                "passport_front" -> {
                                                    KYCPreferences().removeValue("PASSPORT_FRONT")
                                                }
                                            }
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

    private fun buildAnalysis(): UseCase? {
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

    private fun setImage(resource: Int) {
        binding.apply {
            ivSample.setImageResource(resource)
        }
    }
}