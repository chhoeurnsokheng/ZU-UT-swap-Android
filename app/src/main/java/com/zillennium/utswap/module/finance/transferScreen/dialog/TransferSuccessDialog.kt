package com.zillennium.utswap.module.finance.transferScreen.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.DialogFinanceTransferSuccessBinding
import com.zillennium.utswap.models.financeTransfer.Transfer
import com.zillennium.utswap.module.finance.transferScreen.TransferActivity
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.Constants

class TransferSuccessDialog(var data: Transfer.TransferSuccessulReview): DialogFragment() {

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
//                    startActivity(Intent(requireActivity(), TransferActivity::class.java))
//                    Constants.Transfer.trxTransfer = ""
//                    Constants.Transfer.amount = ""
//                    Constants.Transfer.toAccount = ""
//                    Constants.Transfer.fromAccount = ""
//                    Constants.Transfer.trxDate = ""
                  //  dismiss()
                    activity?.finish()
                    startActivity(Intent(requireActivity(), TransferActivity::class.java))
                }


                txtTransferBalance.text =    data.amount
                txtTrxId.text =   data.trx_transfer
                txtTransactionDate.text =     data.trx_date
                txtFromAccount.text =   data.sender
                txtToAccount.text = data.receiver

            }
        }catch (error: Exception){
            Log.d("error", error.toString())
        }
    }



}