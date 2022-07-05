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

            closeImage.setOnClickListener {
                dismiss()
            }

            val status = arguments?.getInt("status")
            if (status == 1){
                txtStatus.text = "Locked"
            }else{
                txtStatus.text = "Unlocked"
                txtStatus.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
            }

            arguments?.getInt("image")?.let { imgIcon.setImageResource(it) }
            txtTitle.text = arguments?.getString("title")
            txtAmount.text = arguments?.getString("amount")
            txtDuration.text = arguments?.getString("duration")
        }
    }

    companion object {
        fun newInstance(
            title: String?,
            status: Int,
            amount: Double,
            duration: String?,
            image: Int,
        ): FinanceSubscriptionsDialog {
            val financeLockUpBuyBackDialog = FinanceSubscriptionsDialog()
            val args = Bundle()
            args.putString("title", title)
            args.putInt("status", status)
            args.putString("amount", groupingSeparator(amount))
            args.putString("duration", duration)
            args.putInt("image", image)

            financeLockUpBuyBackDialog.arguments = args
            return financeLockUpBuyBackDialog
        }
    }
}