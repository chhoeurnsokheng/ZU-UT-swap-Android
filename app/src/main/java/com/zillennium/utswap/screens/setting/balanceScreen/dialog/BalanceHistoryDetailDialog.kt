package com.zillennium.utswap.screens.setting.balanceScreen.dialog

import android.annotation.SuppressLint
import android.content.res.ColorStateList
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


class BalanceHistoryDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var imgClose: ImageView? = null
    private var imgLogo: ImageView? = null
    private var txtTransactionDetail: TextView? = null
    private var txtDate: TextView? = null
    private var txtMoney: TextView? = null
    private var txtBalance: TextView? = null
    private var txtTransaction: TextView? = null
    private var txtCaptionMoney: TextView? = null

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_balance_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        imgLogo = view?.findViewById(R.id.img_logo_cash)
        imgClose = view?.findViewById(R.id.img_close)
        txtBalance = view?.findViewById(R.id.txt_balance_dialog)
        txtDate = view?.findViewById(R.id.txt_date_dialog)
        txtMoney = view?.findViewById(R.id.txt_money_dialog)
        txtTransactionDetail = view?.findViewById(R.id.txt_transaction_detail_dialog)
        txtTransaction = view?.findViewById(R.id.txt_transaction_dialog)
        txtCaptionMoney = view?.findViewById(R.id.caption_txt_money)
    }

    @SuppressLint("SetTextI18n")
    fun initAction() {
        imgClose!!.setOnClickListener { dismiss() }
        txtTransaction!!.text = arguments?.getString("transactionDetail")
        txtTransactionDetail!!.text = arguments?.getString("transaction")
        txtBalance!!.text = arguments?.getString("balance")
        txtDate!!.text = arguments?.getString("date")
        if (arguments?.getString("moneyOut").toString() == " ") {
            txtCaptionMoney!!.text = "Money In: "
            txtMoney!!.setTextColor(Color.GREEN)
            imgLogo!!.imageTintList = ColorStateList.valueOf(Color.GREEN)
            txtMoney!!.text = arguments?.getString("moneyIn")
            arguments?.getInt("logo")?.let { imgLogo!!.setImageResource(it) }
        } else txtMoney!!.text = arguments?.getString("moneyOut")
    }

    companion object {
        fun newInstance(
            transaction: String?,
            transactionDetail: String?,
            date: String?,
            moneyOut: String?,
            balance: String?,
            moneyIn: String?,
            logo: Int
        ): BalanceHistoryDetailDialog {
            val balanceHistoryDetailDialog = BalanceHistoryDetailDialog()
            val args = Bundle()
            args.putString("transaction", transaction)
            args.putString("transactionDetail", transactionDetail)
            args.putString("date", date)
            args.putString("moneyOut", moneyOut)
            args.putString("balance", balance)
            args.putString("moneyIn", moneyIn)
            args.putInt("logo", logo)
            balanceHistoryDetailDialog.arguments = args
            return balanceHistoryDetailDialog
        }
    }
}