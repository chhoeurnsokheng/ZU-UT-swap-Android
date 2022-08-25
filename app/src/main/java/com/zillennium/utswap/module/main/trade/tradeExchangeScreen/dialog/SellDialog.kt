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

class SellDialog : DialogFragment() {
    internal var view: View? = null
    private var btnSell: MaterialButton? = null
    private var btnCancel: MaterialButton? = null
    private var txtVolume: TextView? = null
    private var txtPrice: TextView? = null
    private var txtGrossValue: TextView? = null
    private var txtNetValue: TextView? = null
    private var txtProjectName: TextView? = null
    private var volume: String? = ""
    private var price: String? = ""

    private var subscriptions: Subscription? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_exchange_sell, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnSell = view?.findViewById(R.id.btn_sell_confirm)
        btnCancel = view?.findViewById(R.id.btn_cancel)
        txtVolume = view?.findViewById(R.id.txt_volume)
        txtPrice = view?.findViewById(R.id.txt_price)
        txtGrossValue = view?.findViewById(R.id.txt_gross_volume)
        txtNetValue = view?.findViewById(R.id.txt_net_value)
        txtProjectName = view?.findViewById(R.id.txt_project_name)

        volume = arguments?.getString("volume").toString()
        price = arguments?.getString("price").toString()

        txtVolume?.text = groupingSeparatorInt(volume.toString().toInt())
        txtPrice?.text = price?.toDouble()?.let { groupingSeparator(it) }
        txtProjectName?.text = Constants.OrderBookTable.projectName
        val grossValue = ((volume?.toDouble()?.times(price!!.toDouble())))
        txtGrossValue?.text = grossValue?.let { groupingSeparator(it) }
        txtNetValue?.text = grossValue?.let { groupingSeparator(it) }

        //convert md 5
        var params: Map<String, String> = emptyMap()
        params = mapOf(
            "sign_type" to "MD5",
            "market" to Constants.OrderBookTable.marketNameOrderBook,
            "price" to price.toString(),
            "num" to volume.toString(),
            "type" to "2",
            "tradeType" to "limit",
            "from" to "upTrade"
        )
        val result = VerifyClientData.makeSign(params, SessionPreferences().SESSION_X_TOKEN_API.toString())

        btnSell?.setOnClickListener {
            createTradeOrder(
                TradingList.TradeCreateOrderObj(
                    "MD5",
                    result,
                    Constants.OrderBookTable.marketNameOrderBook,
                    price.toString(),
                    volume.toString(),
                    "2",
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
                Toast.makeText(UTSwapApp.instance,it.message.toString(), Toast.LENGTH_LONG).show()
                SessionVariable.callAvailableBalance.value = true
                SessionVariable.createPendingOrder.value = true
            }else{
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
            price: String?
        ): SellDialog {
            val sellDialog = SellDialog()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("price", price)
            sellDialog.arguments = args
            return sellDialog
        }
    }
}