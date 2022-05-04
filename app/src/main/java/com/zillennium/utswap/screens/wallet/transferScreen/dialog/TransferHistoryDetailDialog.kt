package com.zillennium.utswap.screens.wallet.transferScreen.dialog

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

class TransferHistoryDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var txtTransaction: TextView? = null
    private var txtTime: TextView? = null
    private var txtSender: TextView? = null
    private var txtReceiver: TextView? = null
    private var txtAmount: TextView? = null
    private var txtTotal: TextView? = null
    private var img: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_transfer_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        txtTransaction = view?.findViewById(R.id.txt_transaction_id_dialog)
        txtAmount = view?.findViewById(R.id.txt_amount_dialog)
        txtReceiver = view?.findViewById(R.id.txt_receiver_dialog)
        txtSender = view?.findViewById(R.id.txt_sender_dialog)
        txtTotal = view?.findViewById(R.id.txt_total_dialog)
        txtTime = view?.findViewById(R.id.txt_time_dialog)
        img = view?.findViewById(R.id.img_close)
    }

    fun initAction() {
        txtTransaction!!.text = arguments?.getString("id")
        txtAmount!!.text = arguments?.getString("amount")
        txtReceiver!!.text = arguments?.getString("receiver")
        txtSender!!.text = arguments?.getString("sender")
        txtTotal!!.text = arguments?.getString("total")
        txtTime!!.text = arguments?.getString("time")
        img!!.setOnClickListener { dismiss() }
    }

    companion object {
        fun newInstance(
            transaction: String?,
            time: String?,
            sender: String?,
            receiver: String?,
            amount: String?,
            total: String?
        ): TransferHistoryDetailDialog {
            val transferHistoryDetailDialog = TransferHistoryDetailDialog()
            val args = Bundle()
            args.putString("id", transaction)
            args.putString("time", time)
            args.putString("sender", sender)
            args.putString("receiver", receiver)
            args.putString("amount", amount)
            args.putString("total", total)
            transferHistoryDetailDialog.arguments = args
            return transferHistoryDetailDialog
        }
    }
}