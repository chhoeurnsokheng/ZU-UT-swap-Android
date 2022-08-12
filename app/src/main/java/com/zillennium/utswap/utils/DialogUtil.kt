package com.zillennium.utswap.utils


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.LayoutCustomDialogBinding
import com.zillennium.utswap.databinding.LayoutCustomeDialogKycErrorFailedBinding

class DialogUtil {

    fun customDialog(
        icon: Int,
        title: String,
        message: String,
        labelCancel: String,
        buttonTitle: String,
        onAlertDialogClick: OnAlertDialogClick,
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
                btnPositive.text = buttonTitle
                tvMessage.text = message
                tvTitle.text = title
                tvCancel.text = labelCancel
                ivIcon.setImageResource(icon)
                btnPositive.setOnClickListener {
                    onAlertDialogClick.onLabelCancelClick()
                    builder.dismiss()
                }
                tvCancel.setOnClickListener {
                    builder.dismiss()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    interface OnAlertDialogClick {
        fun onLabelCancelClick()
    }
}


class DialogUtilKyc {

    fun customDialog(
        icon: Int,
        title: String,
        message: String,
        labelCancel: String,
        onAlertDialogClick: DialogUtil.OnAlertDialogClick,
        context: Context
    ) {

        try {
            val alertDialog =
                MaterialAlertDialogBuilder(context, R.style.MaterialAlertdialog_rounded)
            val mBinding = DataBindingUtil.inflate<LayoutCustomeDialogKycErrorFailedBinding>(
                LayoutInflater.from(context),
                R.layout.layout_custome_dialog_kyc_error_failed,
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
                tvMessage.text = message
                tvTitle.text = title
                tvCancel.text = labelCancel
                ivIcon.setImageResource(icon)
                tvCancel.setOnClickListener {
                    onAlertDialogClick.onLabelCancelClick()
                    builder.dismiss()
                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}