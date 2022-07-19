package com.zillennium.utswap.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.DisplayMetrics
import androidx.core.text.HtmlCompat

/**
 * Created by Sokheng Chhoeurn on 19/7/22.
 * Build in Mac
 */


object UtilConvert {
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    @JvmStatic
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    @JvmStatic
    fun convertPixelsToDp(px: Int, context: Context): Int {
        return px / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }

    @JvmStatic
    fun pixelTodp(c: Context, pixel: Float): Float {
        val density = c.resources.displayMetrics.density
        return pixel / density
    }

    @JvmStatic
    fun stripHtml(html: String?): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString().trim()
        } else {
            HtmlCompat.fromHtml(html.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim()
        }
    }
}