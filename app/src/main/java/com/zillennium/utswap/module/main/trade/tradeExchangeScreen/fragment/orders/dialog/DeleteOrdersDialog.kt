package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiTradeImp
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.utils.Constants
import rx.Subscription

class DeleteOrdersDialog : DialogFragment() {
    internal var view: View? = null
    private var btnConfirm: MaterialButton? = null
    private var btnBack: MaterialButton? = null
    private var subscriptions: Subscription? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_exchange_delete_orders, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnConfirm = view?.findViewById(R.id.btn_confirm)
        btnBack = view?.findViewById(R.id.btn_cancel)

        btnConfirm?.setOnClickListener {
            SessionVariable.waitingPlaceOrder.value = true
            cancelOrder(TradingList.TradeCancelOrderObj(arguments?.getString("tradeId")?.toInt()!!),UTSwapApp.instance)
        }

        btnBack?.setOnClickListener {
            dismiss()
        }

        return view
    }

    private fun cancelOrder(body: TradingList.TradeCancelOrderObj, context: Context){
        subscriptions?.unsubscribe()
        subscriptions = ApiTradeImp().cancelTradeOrder(body,context).subscribe({
            if(it.status == 1){
                SessionVariable.cancelPlaceOrder.value = true
                dismiss()
            }else{
                SessionVariable.waitingPlaceOrder.value = false
                SessionVariable.callDialogErrorCreateOrder.value = true
                Constants.TradeExchange.errorMessagePlaceOrder = it.message.toString()
                dismiss()
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
            tradeId: String?
        ): DeleteOrdersDialog {
            val deleteOrdersDialog = DeleteOrdersDialog()
            val args = Bundle()
            args.putString("tradeId", tradeId)
            deleteOrdersDialog.arguments = args
            return deleteOrdersDialog
        }
    }

}