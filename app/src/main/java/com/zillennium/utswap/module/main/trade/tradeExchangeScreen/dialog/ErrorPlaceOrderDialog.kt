package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import com.zillennium.utswap.utils.Constants

class ErrorPlaceOrderDialog : DialogFragment() {
    internal var view: View? = null
    private var txtOk: TextView? = null
    private var txtMessage: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_error_place_order, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        txtOk = view?.findViewById(R.id.txt_ok)
        txtMessage = view?.findViewById(R.id.txt_message)

        txtMessage?.text = Constants.TradeExchange.errorMessagePlaceOrder

        txtOk?.setOnClickListener {
            if(Constants.TradeExchange.errorMessagePlaceOrder == "Please sign in"){
                ClientClearData.clearDataUser()
                SessionVariable.CLEAR_TOKEN_TRADE_EXCHANGE.value = true
                startActivity(Intent(requireContext(), MainActivity::class.java))
                clearData()
            }
            dismiss()
        }

        return view
    }

    private fun clearData(){
        SessionVariable.requestOrderBookSocket.value = false
        SessionVariable.requestTradingList.value = true
        SessionVariable.callDialogErrorCreateOrder.value = false
        SessionVariable.marketPriceSell.value = "0.00"
        SessionVariable.marketPriceBuy.value = "0.00"
        Constants.TradeExchange.sellFee = ""
        Constants.TradeExchange.buyFee = ""
    }
}