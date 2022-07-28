package com.zillennium.utswap.utils

import android.content.Context
import android.util.TypedValue

/**
 * Created by Sokheng Chhoeurn on 27/7/22.
 * Build in Mac
 */

class UtilKt {

    fun dpToPx(context: Context, dp: Int): Int {
        val r = context.resources
        val px =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
        return px.toInt()
    }
}