package com.zillennium.utswap.module.main.trade.tradeScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiTradeImp
import com.zillennium.utswap.api.manager.SocketManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.bases.websocket.WSModel
import com.zillennium.utswap.models.tradingList.TradingList
import okhttp3.WebSocket
import rx.Subscription

class TradePresenter : BaseMvpPresenterImpl<TradeView.View>(),
    TradeView.Presenter {

    private var subscription: Subscription? = null
    private var subscriptionUpcomingProject: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun startSocketTrading() {
        subscription?.unsubscribe()
        subscription = SocketManager().mTradeListSocket.subscribe(object : WSModel<TradingList.TradingListRes>(){
            override fun onOpen(webSocket: WebSocket?) {
                webSocket?.send(ApiSettings.SEND_LIST_TRADE)
            }
            override fun onReconnect() {
            }
            override fun onClose() {
                startSocketTrading()
            }

            override fun onMessage(text: TradingList.TradingListRes?) {
               mView?.fetchTradeData?.value = text
            }

            override fun onFailure(throwable: Throwable?) {
                object : CallbackWrapper(throwable!!, UTSwapApp.instance, arrayListOf()){
                    override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
//                            mView?.onFail(data.toString())
                    }
                }
                closeSocketTrading()
            }


        })
    }

    override fun closeSocketTrading() {
        if(subscription!=null&&!subscription?.isUnsubscribed!!) {
            subscription?.unsubscribe()
        }
    }

    override fun onGetUpcomingProject() {
        subscriptionUpcomingProject?.unsubscribe()
        subscriptionUpcomingProject = ApiTradeImp().upcomingProject().subscribe({
            if(it.status == 1){
                mView?.onGetUpcomingProjectSuccess(it)
            }else{
                mView?.onGetUpcomingProjectFail(it)
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