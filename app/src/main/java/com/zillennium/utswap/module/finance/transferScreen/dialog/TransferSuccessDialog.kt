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
import com.zillennium.utswap.module.finance.transferScreen.TransferActivity
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
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
                    startActivity(Intent(requireActivity(), TransferActivity::class.java))
//                    Constants.Transfer.trxTransfer = ""
//                    Constants.Transfer.amount = ""
//                    Constants.Transfer.toAccount = ""
//                    Constants.Transfer.fromAccount = ""
//                    Constants.Transfer.trxDate = ""
                    dismiss()
                }
//                 var amount = arguments?.getString("amount")
//                 var trxTransfer = arguments?.getString("trxTransfer")
//                 val trxDate = arguments?.getString("trxDate")
//                 var fromAccount = arguments?.getString("fromAccount")
//                 val toAccountunt = arguments?.getString("toAccount")
                amount = KYCPreferences().amount.toString()
                trxTransfer = KYCPreferences().trxTransfer.toString()
                fromAccount = KYCPreferences().fromAccount.toString()
                trxDate = KYCPreferences().trxDate.toString()
                toAccount = KYCPreferences().toAccount.toString()
                sender   = KYCPreferences().sender.toString()

                txtTransferBalance.text =    amount// Constants.Transfer.amount
                txtTrxId.text =   trxTransfer       // Constants.Transfer.trxTransfer
                txtTransactionDate.text =   trxDate //Constants.Transfer.trxDate
                txtFromAccount.text =   fromAccount//   Constants.Transfer.fromAccount
                txtToAccount.text = toAccount// Constants.Transfer.toAccount

            }
        }catch (error: Exception){
            Log.d("error", error.toString())
        }
    }


  companion object{
      var amount = ""
      var trxTransfer = ""
      var fromAccount = ""
      var trxDate = ""
      var toAccount = ""
      var sender = ""
      fun transferInstance(
          amount: String?,
          trxTransfer: String?,
          trxDate: String?,
          fromAccount: String?,
          toAccount: String?,
      ): TransferSuccessDialog {
          val subscriptionConfirmTransferDialog = TransferSuccessDialog()
          val args = Bundle()
          args.putString("amount", amount)
          args.putString("trxTransfer", trxTransfer)
          args.putString("trxDate", trxDate)
          args.putString("fromAccount", fromAccount)
          args.putString("toAccount", toAccount)
          subscriptionConfirmTransferDialog.arguments = args
          return subscriptionConfirmTransferDialog
      }
  }
}