package com.zillennium.utswap.screens.finance.balanceScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogBalanceBinding
import eightbitlab.com.blurview.RenderScriptBlur

class FinanceBalanceDialog: DialogFragment() {

    private var binding: DialogBalanceBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.dialog_balance, container, false)
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

            val status = arguments?.getInt("status")

            if (status == 1){
                txtSymbol.visibility = View.GONE
                txtMoneyType.text = "Money In"
                txtDollar.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
            }else{
                txtSymbol.visibility = View.VISIBLE
                txtMoneyType.text = "Money Out"
                txtDollar.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
            }

            val length = arguments?.getDouble("amountBalance").toString().length

            if ((arguments?.getDouble("amountBalance").toString()[0] == '-')) {
                if (length > 1) {
                    amountBalance.text = arguments?.getDouble("amountBalance").toString().substring(1, length)
                }
            }else{
                amountBalance.text = arguments?.getDouble("amountBalance").toString()
            }

            titleTransaction.text = arguments?.getString("title_transaction")
            dateTransaction.text = arguments?.getString("date_transaction")

        }
    }

    companion object {
        fun newInstance(
        title: String?,
        date: String?,
        amount: Double,
        status: Int,
        ): FinanceBalanceDialog {
            val financeBalanceDialog = FinanceBalanceDialog()
            val args = Bundle()
            args.putString("title_transaction", title)
            args.putString("date_transaction", date)
            args.putDouble("amountBalance", amount)
            args.putInt("status", status)
            financeBalanceDialog.arguments = args
            return financeBalanceDialog
        }
    }
}