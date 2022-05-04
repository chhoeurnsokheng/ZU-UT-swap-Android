package com.zillennium.utswap.screens.wallet.subScriptionScreen.dialog

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


class SubscriptionDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var txtNameUt: TextView? = null
    private var txtTransactionId: TextView? = null
    private var txtPrice: TextView? = null
    private var txtVolume: TextView? = null
    private var txtValue: TextView? = null
    private var txtStart: TextView? = null
    private var imgClose: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_subscription_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        txtNameUt = view?.findViewById(R.id.txt_name_ut_subscript)
        txtPrice = view?.findViewById(R.id.txt_price_dialog)
        txtStart = view?.findViewById(R.id.txt_start_dialog)
        txtTransactionId = view?.findViewById(R.id.txt_transaction_id_dialog)
        txtVolume = view?.findViewById(R.id.txt_volume_dialog)
        txtValue = view?.findViewById(R.id.txt_value_dialog)
        imgClose = view?.findViewById(R.id.img_close)
    }

    fun initAction() {
        imgClose!!.setOnClickListener { dismiss() }
        txtNameUt!!.text = arguments?.getString("name")
        txtVolume!!.text = arguments?.getString("volume")
        txtPrice!!.text = arguments?.getString("price")
        txtTransactionId!!.text = arguments?.getString("id")
        txtStart!!.text = arguments?.getString("date")
        txtValue!!.text = arguments?.getString("value")
    }

    companion object {
        fun newInstance(
            nameSub: String?,
            value: String?,
            date: String?,
            price: String?,
            volume: String?,
            transactionId: String?
        ): SubscriptionDetailDialog {
            val subscriptionDetailDialog = SubscriptionDetailDialog()
            val args = Bundle()
            args.putString("name", nameSub)
            args.putString("value", value)
            args.putString("date", date)
            args.putString("price", price)
            args.putString("volume", volume)
            args.putString("id", transactionId)
            subscriptionDetailDialog.arguments = args
            return subscriptionDetailDialog
        }
    }
}
