package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
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
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt
import rx.Subscription
import kotlin.math.roundToInt

class BuyDialog : DialogFragment() {
    internal var view: View? = null
    private var btnBuy: MaterialButton? = null
    private var btnCancel: MaterialButton? = null
    private var txtVolume: TextView? = null
    private var txtPrice: TextView? = null
    private var txtGrossValue: TextView? = null
    private var txtNetValue: TextView? = null
    private var txtProjectName: TextView? = null
    private var txtFee: TextView? = null

    private var volume: String? = ""
    private var price: String? = ""
    private var fee: Double? = 0.00

    private var subscriptions: Subscription? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_finance_buy, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnBuy = view?.findViewById(R.id.btn_buy_confirm)
        btnCancel = view?.findViewById(R.id.btn_cancel)
        txtVolume = view?.findViewById(R.id.txt_volume)
        txtPrice = view?.findViewById(R.id.txt_price)
        txtGrossValue = view?.findViewById(R.id.txt_gross_volume)
        txtNetValue = view?.findViewById(R.id.txt_net_value)
        txtProjectName = view?.findViewById(R.id.txt_project_name)
        txtFee = view?.findViewById(R.id.txt_fee)

        volume = arguments?.getString("volume").toString()
        price = arguments?.getString("price").toString()

        txtVolume?.text = groupingSeparatorInt(volume.toString().toInt())
        txtPrice?.text = price?.toDouble()?.let { groupingSeparator(it) }
        txtProjectName?.text = Constants.OrderBookTable.projectName

//        txtGrossValue?.text = (volume?.toFloat()?.times(price!!.toFloat())).toString()
        val grossValue = ((volume?.toDouble()?.times(price!!.toDouble())))
        fee = (grossValue?.times(Constants.TradeExchange.buyFee.toDouble()))?.div(100)

        txtFee?.text = fee?.let { groupingSeparator(it) }

        txtGrossValue?.text = grossValue?.let { groupingSeparator(it) }
        txtNetValue?.text = grossValue?.let { groupingSeparator(it) }

        //convert md 5
        var params: Map<String, String> = emptyMap()
        params = mapOf(
            "sign_type" to "MD5",
            "market" to Constants.OrderBookTable.marketNameOrderBook,
            "price" to price.toString(),
            "num" to volume.toString(),
            "type" to "1",
            "tradeType" to "limit",
            "from" to "upTrade"
        )
        val result = VerifyClientData.makeSign(params, SessionPreferences().SESSION_X_TOKEN_API.toString())

        btnBuy?.setOnClickListener {
            SessionVariable.waitingPlaceOrder.value = true
            createTradeOrder(
                TradingList.TradeCreateOrderObj(
                "MD5",
                    result,
                    Constants.OrderBookTable.marketNameOrderBook,
                    price.toString(),
                    volume.toString(),
                    "1",
                    "limit"
            ),UTSwapApp.instance)
            dismiss()
        }

        btnCancel?.setOnClickListener {
            dismiss()
        }

        return view
    }

    private fun createTradeOrder(body: TradingList.TradeCreateOrderObj, context: Context){
        subscriptions?.unsubscribe()
        subscriptions = ApiTradeImp().createOrder(body,context).subscribe({
            if(it.status == 1){
                SessionVariable.callDialogSuccessPlaceOrder.value = true
                SessionVariable.refreshMatchingTransaction.value = true
            }else{
                //Toast.makeText(UTSwapApp.instance,it.message.toString(),Toast.LENGTH_LONG).show()
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
            price: String?,
            tradeType: String?
        ): BuyDialog {
            val buyDialog = BuyDialog()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("price", price)
            args.putString("tradeType",tradeType)
            buyDialog.arguments = args
            return buyDialog
        }
    }
}