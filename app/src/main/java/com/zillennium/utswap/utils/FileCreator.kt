package com.zillennium.utswap.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import com.zillennium.utswap.UTSwapApp
import java.io.*

object FileCreator {

    fun createTempImageFile(): File? {
        val imageFileName = System.currentTimeMillis().toString() + "_"
        val storageDir: File? =
            UTSwapApp.instance.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        try {
            return File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
            )
        } catch (e: IOException) {
        }
        return null
    }

    fun saveImage(bytes: ByteArray): String {
        val outStream: FileOutputStream
        val file = createTempImageFile()
        try {
            file?.deleteOnExit()
            file?.createNewFile()
            outStream = FileOutputStream(file)
            outStream.write(bytes)
            MediaScannerConnection.scanFile(
                UTSwapApp.instance,
                arrayOf(file?.path),
                arrayOf("image/jpeg"), null
            )
            outStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file?.absolutePath ?: ""
    }

    fun uriToBytesArray(uri: Uri): ByteArray {
        val inputStream =
            UTSwapApp.instance.contentResolver.openInputStream(uri)
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        inputStream?.apply {
            while (read(buffer).also { len = it } != -1) {
                byteBuffer.write(buffer, 0, len)
            }
        }
        return byteBuffer.toByteArray()
    }

    fun bitMapToBytesArray(bitMap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitMap.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            stream
        )
        return stream.toByteArray()
    }

    fun rotate(bitMap: Bitmap, photoPath: String, isSelfie: Boolean = false): Bitmap {
        val matrix = Matrix()

        /** make rotate image */
        val ei = ExifInterface(photoPath)
        val orientation: Int = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                matrix.postRotate(90f)
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                matrix.postRotate(180f)
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                matrix.postRotate(270f)
            }
            ExifInterface.ORIENTATION_NORMAL -> {
                matrix.postRotate(0f)
            }
            else -> {
                matrix.postRotate(0f)
            }
        }

        /** make reverse image from mirror */
        if (isSelfie) {
            val mirrorY = floatArrayOf(-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
            val matrixMirrorY = Matrix()
            matrixMirrorY.setValues(mirrorY)
            matrix.postConcat(matrixMirrorY)
        }

        /** Resize the bitmap */
        val scaledBitmap = Bitmap.createScaledBitmap(
            bitMap,
            bitMap.width,
            bitMap.height,
            true
        )

        /** Create and return the rotated bitmap */
        return Bitmap.createBitmap(
            scaledBitmap,
            0,
            0,
            scaledBitmap.width,
            scaledBitmap.height,
            matrix,
            true
        )
    }
}