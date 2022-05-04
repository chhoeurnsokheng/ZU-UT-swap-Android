package com.zillennium.utswap.screens.wallet.withdrawalScreen.dialog

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


class WithdrawHistoryDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var txtTransactionId: TextView? = null
    private var txtTime: TextView? = null
    private var txtAmount: TextView? = null
    private var txtAmountArrival: TextView? = null
    private var txtReceivingAddress: TextView? = null
    private var img: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_withdraw_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        txtAmount = view?.findViewById(R.id.txt_amount_dialog)
        txtAmountArrival = view?.findViewById(R.id.txt_amount_arrival_dialog)
        txtTransactionId = view?.findViewById(R.id.txt_transaction_id_dialog)
        txtTime = view?.findViewById(R.id.txt_time_dialog)
        txtReceivingAddress = view?.findViewById(R.id.txt_receiving_address_dialog)
        img = view?.findViewById(R.id.img_close)
    }

    fun initAction() {
        img!!.setOnClickListener { dismiss() }
        txtTransactionId!!.text = arguments?.getString("id")
        txtTime!!.text = arguments?.getString("time")
        txtAmount!!.text = arguments?.getString("amount")
        txtAmountArrival!!.text = arguments?.getString("amountArrival")
        txtReceivingAddress!!.text = arguments?.getString("receivingAddress")
    }

    companion object {
        fun newInstance(
            transaction: String?,
            time: String?,
            amount: String?,
            amountArrival: String?,
            receivingAddress: String?
        ): WithdrawHistoryDetailDialog {
            val withdrawHistoryDetailDialog = WithdrawHistoryDetailDialog()
            val args = Bundle()
            args.putString("id", transaction)
            args.putString("time", time)
            args.putString("amount", amount)
            args.putString("amountArrival", amountArrival)
            args.putString("receivingAddress", receivingAddress)
            withdrawHistoryDetailDialog.arguments = args
            return withdrawHistoryDetailDialog
        }
    }
}
