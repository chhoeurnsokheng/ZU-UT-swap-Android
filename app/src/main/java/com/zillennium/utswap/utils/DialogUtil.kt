package com.zillennium.utswap.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.LayoutCustomDialogBinding

class DialogUtil {

    fun customDialog(
        icon: Int,
        title: String,
        message: String,
        buttonTitle: String,
        context: Context
    ) {

        try {
            val alertDialog =
                MaterialAlertDialogBuilder(context, R.style.MaterialAlertdialog_rounded)
            val mBinding = DataBindingUtil.inflate<LayoutCustomDialogBinding>(
                LayoutInflater.from(context),
                R.layout.layout_custom_dialog,
                null,
                false
            )
            val builder = alertDialog.create()
            builder.window?.setLayout(
                dpToPx(350),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            builder.setView(mBinding?.root)
            builder.setCancelable(true)
            try {
                builder.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            mBinding?.apply {
                btnCancel.text = buttonTitle
                tvMessage.text = message
                tvTitle.text = title
                ivIcon.setImageResource(icon)
                btnCancel.setOnClickListener {
                    builder.dismiss()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}