package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiTradeImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.tradingList.TradingList
import rx.Subscription

class OrdersPresenter : BaseMvpPresenterImpl<OrdersView.View>(),
    OrdersView.Presenter {

    private var subscriptions: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetOrderPending(body: TradingList.TradeOrderPendingObj, context: Context) {
        subscriptions?.unsubscribe()
        subscriptions = ApiTradeImp().getTradeOrderPending(body,context).subscribe({
            if(it.status == 1){
                mView?.onGetOrderPendingSuccess(it)
            }else{
                mView?.onGetOrderPendingFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
}