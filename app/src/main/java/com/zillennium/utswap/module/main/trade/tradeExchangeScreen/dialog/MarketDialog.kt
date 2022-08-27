package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiTradeImp
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.VerifyClientData
import com.zillennium.utswap.utils.groupingSeparatorInt
import rx.Subscription

class MarketDialog : DialogFragment() {
    internal var view: View? = null
    private var btnBuy: MaterialButton? = null
    private var btnCancel: MaterialButton? = null
    private var txtVolume: TextView? = null
    private var txtProjectName: TextView? = null

    private var subscriptions: Subscription? = null
    private var type: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_exchange_market, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnBuy = view?.findViewById(R.id.btn_buy_confirm)
        btnCancel = view?.findViewById(R.id.btn_cancel)
        txtVolume = view?.findViewById(R.id.txt_volume)
        txtProjectName = view?.findViewById(R.id.txt_project_name)

        txtVolume?.text = arguments?.get("volume").toString().toInt().let { groupingSeparatorInt(it) }
        btnBuy?.text = arguments?.get("status").toString()
        btnBuy?.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
        txtProjectName?.text = Constants.OrderBookTable.projectName

        if(arguments?.get("status") == "SELL")
        {
            btnBuy?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            type = "2"
        }else{
            btnBuy?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
            type = "1"
        }

        //convert md 5
        var params: Map<String, String> = emptyMap()
        params = mapOf(
            "sign_type" to "MD5",
            "market" to Constants.OrderBookTable.marketNameOrderBook,
            "price" to "",
            "num" to arguments?.get("volume").toString(),
            "type" to type.toString(),
            "tradeType" to "market",
            "from" to "upTrade"
        )
        val result = VerifyClientData.makeSign(params, SessionPreferences().SESSION_X_TOKEN_API.toString())

        println("===result  $result" + "token"+ SessionPreferences().SESSION_TOKEN.toString())

        btnCancel?.setOnClickListener {
            dismiss()
        }

        btnBuy?.setOnClickListener {
            SessionVariable.waitingPlaceOrder.value = true
            createTradeOrder(
                TradingList.TradeCreateOrderObj(
                    "MD5",
                    result,
                    Constants.OrderBookTable.marketNameOrderBook,
                    "",
                    arguments?.get("volume").toString(),
                    type.toString(),
                    "market"
                ),UTSwapApp.instance)
            dismiss()
        }


        return view
    }

    private fun createTradeOrder(body: TradingList.TradeCreateOrderObj, context: Context){
        subscriptions?.unsubscribe()
        subscriptions = ApiTradeImp().createOrder(body,context).subscribe({
            if(it.status == 1){
                //Toast.makeText(UTSwapApp.instance,it.message.toString(), Toast.LENGTH_LONG).show()
                if(it.data?.num_after_deal == null)
                {
                    SessionVariable.refreshMatchingTransaction.value = true
                    SessionVariable.callDialogSuccessPlaceOrder.value = true
                }else{
                    var placeOrderAgain: Map<String, String> = emptyMap()
                    placeOrderAgain = mapOf(
                        "sign_type" to "MD5",
                        "market" to Constants.OrderBookTable.marketNameOrderBook,
                        "price" to "",
                        "num" to it.data?.num_after_deal.toString(),
                        "type" to type.toString(),
                        "tradeType" to "market",
                        "from" to "upTrade"
                    )
                    val result = VerifyClientData.makeSign(placeOrderAgain, SessionPreferences().SESSION_X_TOKEN_API.toString())
                    createTradeOrder(
                        TradingList.TradeCreateOrderObj(
                            "MD5",
                            result,
                            Constants.OrderBookTable.marketNameOrderBook,
                            "",
                            it.data?.num_after_deal.toString(),
                            type.toString(),
                            "market"
                        ),UTSwapApp.instance)
                }
            }
            else{
                SessionVariable.callDialogErrorCreateOrder.value = true
                Constants.TradeExchange.errorMessagePlaceOrder = it.message.toString()
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                }
            }
        })
    }


    companion object {
        fun newInstance(
            volume: String?,
            txtStatus: String?,
            tradeType: String?
        ): MarketDialog {
            val marketDialog = MarketDialog()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("status", txtStatus)
            args.putString("tradeType",tradeType)
            marketDialog.arguments = args
            return marketDialog
        }
    }
}