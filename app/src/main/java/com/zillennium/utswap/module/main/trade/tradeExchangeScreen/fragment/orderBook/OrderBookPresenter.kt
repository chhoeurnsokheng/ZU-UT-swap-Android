package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook

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

class OrderBookPresenter : BaseMvpPresenterImpl<OrderBookView.View>(),
    OrderBookView.Presenter {

    private var subscriptionOrderBookTable: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun startTradeOrderBookTable(marketName: String?) {
        subscriptionOrderBookTable?.unsubscribe()
        subscriptionOrderBookTable = SocketManager().mTradeListOrderBookTable.subscribe(object : WSModel<TradingList.TradeOrderBookTableRes>(){
            override fun onOpen(webSocket: WebSocket?) {
                webSocket?.send(ApiSettings.SEND_TRADE_MARKET_ORDER_BOOK_TABLE+marketName.toString())
            }
            override fun onReconnect() {
            }
            override fun onClose() {
                startTradeOrderBookTable(ApiSettings.SEND_TRADE_MARKET_ORDER_BOOK_TABLE+marketName.toString())
            }

            override fun onMessage(text: TradingList.TradeOrderBookTableRes?) {
                mView?.fetchTradeOrderBookTable?.value = text
            }

            override fun onFailure(throwable: Throwable?) {
                object : CallbackWrapper(throwable!!, UTSwapApp.instance, arrayListOf()){
                    override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
//                            mView?.onFail(data.toString())
                    }
                }
                closeTradeOrderBookTable()
            }

        })
    }

    override fun closeTradeOrderBookTable() {
        if(subscriptionOrderBookTable!=null && !subscriptionOrderBookTable?.isUnsubscribed!!){
            subscriptionOrderBookTable?.unsubscribe()
        }
    }
}