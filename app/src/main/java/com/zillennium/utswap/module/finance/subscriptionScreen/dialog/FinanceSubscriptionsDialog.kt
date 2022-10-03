package com.zillennium.utswap.module.finance.subscriptionScreen.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogFinanceSubscriptionBinding
import com.zillennium.utswap.utils.groupingSeparator
import eightbitlab.com.blurview.RenderScriptBlur

class FinanceSubscriptionsDialog : DialogFragment() {

    private var binding: DialogFinanceSubscriptionBinding? = null

    override fun getTheme(): Int {
        return R.style.AlertDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_finance_subscription,
                container,
                false
            )
        return binding?.root
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            activity?.apply {
                blurView.setupWith(findViewById<ViewGroup>(android.R.id.content))
                    .setFrameClearDrawable(window.decorView.background)
                    .setBlurAlgorithm(RenderScriptBlur(UTSwapApp.instance))
                    .setBlurRadius(20f)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true)
            }

            blurView.setOnClickListener {
                dismiss()
            }


            val status = arguments?.getBoolean("status")
            if (status ==true) {
                imgIcon.setImageResource(R.drawable.ic_locked)
                txtStatus.text = "Locked"
                txtDuration.text = "${arguments?.getInt("duration").toString()} Day(s)"
                txtStatus.setTextColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.danger
                    )
                )
                txtStartDate.text = arguments?.getString("start")
                txtEndDate.text = arguments?.getString("end")
            }
            else {
                imgIcon.setImageResource(R.drawable.ic_unlocked)
                txtStatus.text = "Completed"
                txtDuration.text = "—"
                txtStatus.setTextColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.simple_green
                    )
                )
                txtStartDate.text = arguments?.getString("start")
                txtEndDate.text =  "—"       // arguments?.getString("end")
            }

            txtTitle.text = arguments?.getString("title")
            txtTransactionId.text = arguments?.getString("transactionId")
            txtPrice.text = arguments?.getDouble("price")?.let { groupingSeparator(it) }
            txtVolume.text = arguments?.getString("volume")
            txtAmount.text = arguments?.getDouble("value")?.let { groupingSeparator(it) }

        }
    }

    companion object {
        fun newInstance(
            title: String?,
            status: Boolean,
            transactionId: String,
            price: Double,
            volume: String,
            value: Double,
            start: String,
            end: String,
            duration: Int
        ): FinanceSubscriptionsDialog {
            val financeLockUpBuyBackDialog = FinanceSubscriptionsDialog()
            val args = Bundle()
            args.putString("title", title)
            args.putBoolean("status", status)
            args.putString("transactionId", transactionId)
            args.putDouble("price", price)
            args.putString("volume", volume)
            args.putDouble("value", value)
            args.putString("start", start)
            args.putString("end", end)
            args.putInt("duration", duration)

            financeLockUpBuyBackDialog.arguments = args
            return financeLockUpBuyBackDialog
        }
    }
}