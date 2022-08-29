package com.zillennium.utswap.module.finance.transferScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.DialogFinanceTransferSuccessBinding
import com.zillennium.utswap.utils.Constants

class TransferSuccessDialog: DialogFragment() {

    private var binding: DialogFinanceTransferSuccessBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.dialog_finance_transfer_success, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            binding?.apply {
                btnDone.setOnClickListener{
                    Constants.Transfer.trxTransfer = ""
                    Constants.Transfer.amount = ""
                    Constants.Transfer.toAccount = ""
                    Constants.Transfer.fromAccount = ""
                    Constants.Transfer.trxDate = ""
                    dismiss()
                }

                txtTransferBalance.text = Constants.Transfer.amount
                txtTrxId.text = Constants.Transfer.trxTransfer
                txtTransactionDate.text = Constants.Transfer.trxDate
                txtFromAccount.text = Constants.Transfer.fromAccount
                txtToAccount.text = Constants.Transfer.toAccount

            }
        }catch (error: Exception){
            Log.d("error", error.toString())
        }
    }
}