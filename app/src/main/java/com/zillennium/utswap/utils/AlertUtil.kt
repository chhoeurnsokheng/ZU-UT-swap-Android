package com.zillennium.utswap.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.LayDialogRequestErrorBinding


/**
 * Created by Sokheng Chhoeurn on 27/7/22.
 * Build in Mac
 */


class AlertUtil {

    fun alertDefaultDialog(
        context: Context,
        titleTxt: String,
        messageTxt: String,
        positiveTxt: String,
        negativeTxt: String,
        onPositiveButtonClickListener: DialogInterface.OnClickListener,
        onNegativeButtonClickListener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.apply {
            setTitle(titleTxt)
            setMessage(messageTxt)
            if (positiveTxt != "") {
                setButton(AlertDialog.BUTTON_POSITIVE, positiveTxt) { dialog, which ->
                    onPositiveButtonClickListener.onClick(dialog, which)
                }
            }
            if (negativeTxt != "") {
                setButton(AlertDialog.BUTTON_NEGATIVE, negativeTxt) { dialog, which ->
                    onNegativeButtonClickListener.onClick(dialog, which)
                }
            }
            show()
        }
    }

    fun alertRequestError(
        context: Context,
        errorText: String,
        errorCode: String,
        errorStatus: String,
        onOkClickListener: View.OnClickListener?,
        isNoInternet: Boolean
    ) {
        try {
            val mBinding = DataBindingUtil.inflate<LayDialogRequestErrorBinding>(
                LayoutInflater.from(context),
                R.layout.lay_dialog_request_error,
                null,
                false
            )

            val builder = AlertDialog.Builder(context)
            builder.apply {
                setCancelable(false)
                setView(mBinding.root)
            }

            val dialog = builder.create()
            dialog.apply {
                dialog.window?.apply {
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }

                setDialogWidthPercent(50, this, context)
                show()

                if (isNoInternet) {
                    Tovuti.from(context).monitor { _, isConnected, _ ->
                        if (isConnected) {
                            dialog.dismiss()
                        }
                    }
                }
            }

            mBinding.apply {
                errorTv.text = errorText
                btnOk.text = "Ok"

                errorCodeTv.visibility = if (errorCode.isEmpty()) View.GONE else View.VISIBLE
                errorCodeTv.text = "[$errorCode] [$errorStatus]"

                if (onOkClickListener != null) {
                    btnOk.setOnClickListener {
                        dialog.dismiss()
                        onOkClickListener.onClick(it)
                    }
                } else {
                    btnOk.setOnClickListener {
                        dialog.dismiss()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setDialogWidthPercent(
        percentage: Int,
        dialog: Dialog,
        context: Context
    ) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog.window?.setLayout(percentWidth.toInt(), UtilKt().dpToPx(context, 150))
    }



















}


