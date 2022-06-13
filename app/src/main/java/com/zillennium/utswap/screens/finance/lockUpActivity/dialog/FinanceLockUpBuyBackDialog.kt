package com.zillennium.utswap.screens.finance.lockUpActivity.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogFinanceBuyBackBinding
import com.zillennium.utswap.screens.finance.balanceActivity.dialog.FinanceBalanceDialog
import com.zillennium.utswap.utils.groupingSeparator
import eightbitlab.com.blurview.RenderScriptBlur

class FinanceLockUpBuyBackDialog: DialogFragment() {

    private var binding: DialogFinanceBuyBackBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.dialog_finance_buy_back, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            activity?.apply {
                blurView.setupWith(findViewById<ViewGroup>(android.R.id.content))
                    .setFrameClearDrawable(window.decorView.background)
                    .setBlurAlgorithm(RenderScriptBlur(UTSwapApp.instance))
                    .setBlurRadius(20f)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true)
            }

            closeImage.setOnClickListener{
                dismiss()
            }

            txtTitle.text = arguments?.getString("title")
            txtAmount.text = arguments?.getString("amount")
            txtStartDate.text = arguments?.getString("start_date")
            txtEndDate.text = arguments?.getString("end_date")
            txtDuration.text = arguments?.getString("duration")

        }
    }

    companion object {
        fun newInstance(
            title: String?,
            amount: Double,
            startDate: String?,
            endDate: String?,
            duration: String?,
        ): FinanceLockUpBuyBackDialog {
            val financeLockUpBuyBackDialog = FinanceLockUpBuyBackDialog()
            val args = Bundle()
            args.putString("title", title)
            args.putString("amount", groupingSeparator(amount))
            args.putString("start_date", startDate)
            args.putString("end_date", endDate)
            args.putString("duration", duration)

            financeLockUpBuyBackDialog.arguments = args
            return financeLockUpBuyBackDialog
        }
    }
}