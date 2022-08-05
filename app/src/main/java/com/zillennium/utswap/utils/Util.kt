package com.zillennium.utswap.utils

import android.content.Context
import android.graphics.*
import android.media.ExifInterface
import android.text.Html
import android.text.Spanned
import android.util.Base64
import android.util.Log
import android.util.TypedValue
import com.zillennium.utswap.R
import java.io.ByteArrayOutputStream
import java.io.IOException


/**
 * @author chhoeurnsokheng
 * Created 7/7/22 at 4:40 PM
 * By Mac
 */


class Util() {
    private fun saveState(isFavourite: Boolean, context: Context) {
        val aSharedPreferences = context.getSharedPreferences(
            "Favourite", Context.MODE_PRIVATE
        )
        val aSharedPreferencesEdit = aSharedPreferences
            .edit()
        aSharedPreferencesEdit.putBoolean("State", isFavourite)
        aSharedPreferencesEdit.apply()
    }

    private fun readState(context: Context): Boolean {
        val aSharedPreferences = context.getSharedPreferences(
            "Favourite", Context.MODE_PRIVATE
        )
        return aSharedPreferences.getBoolean("State", true)
    }

    fun getHtmlText(
        firstColor: String,
        firstText: String,
        lastText: String,
        lastColor: String
    ): Spanned? {
        return Html.fromHtml("<font color=$firstColor>$firstText</font> <font color=$lastColor>$lastText</font>")
    }

    interface onAlertDialogClick {
        fun onPositiveClicking()
        fun onNegativeClicking()
        fun onMoreClicking()
    }

    companion object {
        @Throws(IOException::class)
        fun encodeImageToBase64(path: String?): String {
            var scaledBitmap: Bitmap? = null
            val base64: String
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            var bitmap = BitmapFactory.decodeFile(path, options)
            var actualHeight = options.outHeight
            var actualWidth = options.outWidth
            val maxHeight = 2048.0f
            val maxWidth = 1152.0f
            var imgRatio = (actualWidth / actualHeight).toFloat()
            val maxRatio = maxWidth / maxHeight
            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight
                    actualWidth = (imgRatio * actualWidth).toInt()
                    actualHeight = maxHeight.toInt()
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth
                    actualHeight = (imgRatio * actualHeight).toInt()
                    actualWidth = maxWidth.toInt()
                } else {
                    actualHeight = maxHeight.toInt()
                    actualWidth = maxWidth.toInt()
                }
            }

            options.inJustDecodeBounds = false
            options.inTempStorage = ByteArray(32 * 1024)
            try {
                bitmap = BitmapFactory.decodeFile(path, options)
            } catch (exception: OutOfMemoryError) {
                exception.printStackTrace()
            }
            try {
                scaledBitmap =
                    Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
            } catch (exception: OutOfMemoryError) {
                exception.printStackTrace()
            }
            val ratioX = actualWidth / options.outWidth.toFloat()
            val ratioY = actualHeight / options.outHeight.toFloat()
            val middleX = actualWidth / 2.0f
            val middleY = actualHeight / 2.0f
            val scaleMatrix = Matrix()
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
            val canvas: Canvas
            if (scaledBitmap != null) {
                canvas = Canvas(scaledBitmap)
                canvas.setMatrix(scaleMatrix)
                canvas.drawBitmap(
                    bitmap, middleX - bitmap.width / 2, middleY - bitmap.height / 2, Paint(
                        Paint.FILTER_BITMAP_FLAG
                    )
                )
            }
            val exif: ExifInterface
            try {
                exif = ExifInterface(path!!)
                val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0
                )
                Log.d("EXIF", "Exif: $orientation")
                val matrix = Matrix()
                if (orientation == 6) {
                    matrix.postRotate(90f)
                    Log.d("EXIF", "Exif: $orientation")
                } else if (orientation == 3) {
                    matrix.postRotate(180f)
                    Log.d("EXIF", "Exif: $orientation")
                } else if (orientation == 8) {
                    matrix.postRotate(270f)
                    Log.d("EXIF", "Exif: $orientation")
                }
                if (scaledBitmap != null) {
                    scaledBitmap = Bitmap.createBitmap(
                        scaledBitmap, 0, 0,
                        scaledBitmap.width, scaledBitmap.height, matrix,
                        true
                    )
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val baos = ByteArrayOutputStream()
            scaledBitmap?.compress(Bitmap.CompressFormat.JPEG, 80, baos)
            val b = baos.toByteArray()
            System.gc()
            base64 = Base64.encodeToString(b, Base64.NO_WRAP)
            //base64 = resizeBase64Image(base64, dataSize);
            return base64
        }


        fun saveData(key: String?, value: String?, activity: Context) {
            val aSharedPreferences = activity.getSharedPreferences(
                activity.getString(R.string.app_name), Context.MODE_PRIVATE
            )
            val aSharedPreferencesEdit = aSharedPreferences
                .edit()
            aSharedPreferencesEdit.putString(key, value)
            aSharedPreferencesEdit.apply()
        }

        fun getData(key: String?, activity: Context): String? {
            return try {
                val aSharedPreferences = activity.getSharedPreferences(
                    activity.getString(R.string.app_name), Context.MODE_PRIVATE
                )
                aSharedPreferences.getString(key, "")
            } catch (e: java.lang.NullPointerException) {
                ""
            }
        }
        fun dpToPx(context: Context, dp: Int): Int {
            val r = context.resources
            val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
            return px.toInt()
        }

    }
}