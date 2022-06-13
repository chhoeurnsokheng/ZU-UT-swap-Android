package com.zillennium.utswap.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.zillennium.utswap.UTSwapApp

fun dpToPx( dp: Int): Int {
    val r: Resources = UTSwapApp.instance.resources
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
    return px.toInt()
}