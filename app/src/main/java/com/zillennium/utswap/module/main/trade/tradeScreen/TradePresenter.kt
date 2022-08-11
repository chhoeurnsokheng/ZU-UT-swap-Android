package com.zillennium.utswap.module.main.trade.tradeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.api.manager.SocketManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.bases.websocket.WSModel
import com.zillennium.utswap.models.tradingList.TradingList
import okhttp3.WebSocket
import rx.Subscription

class TradePresenter : BaseMvpPresenterImpl<TradeView.View>(),
    TradeView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun startSocketTrading() {
        subscription?.unsubscribe()
        subscription = SocketManager().mTradeListSocket.subscribe(object : WSModel<TradingList.TradingListRes>(){
            override fun onOpen(webSocket: WebSocket?) {
                println("onOpen")
                webSocket?.send(ApiSettings.SEND_LIST_TRADE)
            }
            override fun onReconnect() {
            }
            override fun onClose() {
                println("onClose")
                startSocketTrading()
            }

            override fun onMessage(text: TradingList.TradingListRes?) {
               mView?.fetchTradeData?.value = text
            }

        })
    }

    override fun closeSocketTrading() {
        subscription?.unsubscribe()
    }
}