package com.zillennium.utswap.module.main.trade.tradeExchangeScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.SocketManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.bases.websocket.WSModel
import com.zillennium.utswap.models.tradingList.TradingList
import okhttp3.WebSocket
import rx.Subscription

class TradeExchangePresenter : BaseMvpPresenterImpl<TradeExchangeView.View>(),
    TradeExchangeView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun startTradeDetailSocket(marketName: String?) {
        subscription?.unsubscribe()
        subscription = SocketManager().mTradeListSocket.subscribe(object : WSModel<TradingList.TradingListDetailRes>(){
            override fun onOpen(webSocket: WebSocket?) {
                webSocket?.send(ApiSettings.SEND_TRADE_MARKET_NAME+marketName.toString())
            }
            override fun onReconnect() {
            }
            override fun onClose() {
                startTradeDetailSocket(ApiSettings.SEND_TRADE_MARKET_NAME+marketName.toString())
            }

            override fun onMessage(text: TradingList.TradingListDetailRes?) {
                mView?.fetchTradeDetailData?.value =  text?.market_summary
            }

            override fun onFailure(throwable: Throwable?) {
                object : CallbackWrapper(throwable!!, UTSwapApp.instance, arrayListOf()){
                    override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
//                            mView?.onFail(data.toString())
                    }
                }
                closeTradeDetailSocket()
            }

        })
    }

    override fun closeTradeDetailSocket() {
        if(subscription!=null&&!subscription?.isUnsubscribed!!) {
            subscription?.unsubscribe()
        }
    }
}