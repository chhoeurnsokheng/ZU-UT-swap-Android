package com.zillennium.utswap.screens.wallet.depositScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R

class DepositHistoryDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var imgClose: ImageView? = null
    private var imgLogo: ImageView? = null
    private var txtDepositMethod: TextView? = null
    private var txtTransactionId: TextView? = null
    private var txtAmount: TextView? = null
    private var txtFee: TextView? = null
    private var txtNetValue: TextView? = null
    private var txtRechargeTime: TextView? = null
    private var txtStatus: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_deposit_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        imgClose = view?.findViewById(R.id.img_close)
        imgLogo = view?.findViewById(R.id.img_logo_cash)
        txtDepositMethod = view?.findViewById(R.id.txt_deposit_method)
        txtTransactionId = view?.findViewById(R.id.txt_transaction_id_dialog)
        txtAmount = view?.findViewById(R.id.txt_amount_dialog)
        txtFee = view?.findViewById(R.id.txt_fee_dialog)
        txtNetValue = view?.findViewById(R.id.txt_net_value_dialog)
        txtRechargeTime = view?.findViewById(R.id.txt_recharge_time_dialog)
        txtStatus = view?.findViewById(R.id.txt_status_dialog)
    }

    fun initAction() {
        imgClose!!.setOnClickListener { dismiss() }

        //txtDepositMethod.setText(getArguments().getString("method"));
        txtTransactionId!!.text = arguments?.getString("transaction")
        txtAmount!!.text = arguments?.getString("amount")
        txtFee!!.text = arguments?.getString("fee")
        txtNetValue!!.text = arguments?.getString("netValue")
        txtRechargeTime!!.text = arguments?.getString("recharge")
        txtStatus!!.text = arguments?.getString("status")
    }

    companion object {
        fun newInstance(
            img: Int,
            depositMethod: String?,
            transactionId: String?,
            amount: String?,
            fee: String?,
            netValue: String?,
            rechargeTime: String?,
            status: String?
        ): DepositHistoryDetailDialog {
            val depositHistoryDetailDialog = DepositHistoryDetailDialog()
            val args = Bundle()
            args.putInt("logo", img)
            args.putString("method", depositMethod)
            args.putString("transaction", transactionId)
            args.putString("amount", amount)
            args.putString("fee", fee)
            args.putString("netValue", netValue)
            args.putString("recharge", rechargeTime)
            args.putString("status", status)
            depositHistoryDetailDialog.arguments = args
            return depositHistoryDetailDialog
        }
    }
}
