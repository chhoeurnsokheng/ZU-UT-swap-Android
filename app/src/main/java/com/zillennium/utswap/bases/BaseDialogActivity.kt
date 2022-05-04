package com.zillennium.utswap.bases

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window


object BaseDialogActivity {
    fun showDialog(content: Context?, layout: Int) {
        val dialog = Dialog(content!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layout)
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }
}