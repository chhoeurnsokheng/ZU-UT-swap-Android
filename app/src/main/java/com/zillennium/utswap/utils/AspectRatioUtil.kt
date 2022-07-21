package com.zillennium.utswap.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * Created by Sokheng Chhoeurn on 19/7/22.
 * Build in Mac
 */


object AspectRatioUtil {
    fun apply(ash: Int, asw: Int, activity: Activity, view: View) {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = ash * width / asw
        view.layoutParams = RelativeLayout.LayoutParams(width, height)
    }

    fun apply(ash: Int, asw: Int, widthToTake: Int, activity: Activity, view: View) {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = ash * (width - widthToTake) / asw
        view.layoutParams = RelativeLayout.LayoutParams(width, height)
    }

//    fun applyCustomHeight(ash: Int, width: Int, activity: Activity, view: View) {
//        val displayMetrics = DisplayMetrics()
//        activity.windowManager.defaultDisplay.getMetrics(RelativeLayout)
//        val height = displayMetrics.heightPixels * ash / 100
//        if (width == -1) {
//            view.layoutParams =
//                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height)
//        } else {
//            view.layoutParams =
//                RelativeLayout.LayoutParams(Util.dpToPx(view.context, width), height)
//        }
//    }

    fun applyCustomHeightBottomSheet(ash: Int, width: Int, activity: Activity, view: View) {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels * ash / 100
        if (width == -1) {
            val param = view.layoutParams as CoordinatorLayout.LayoutParams
            param.width = CoordinatorLayout.LayoutParams.MATCH_PARENT
            param.height = height
            view.layoutParams = param
        } else {
            val param = view.layoutParams as CoordinatorLayout.LayoutParams
            param.width = Util.dpToPx(view.context, width)
            param.height = height
            view.layoutParams = param
        }
    }
}
