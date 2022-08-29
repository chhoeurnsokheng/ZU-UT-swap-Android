package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class OrdersView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetOrderPendingSuccess(data: TradingList.TradeOrderPendingRes)
        fun onGetOrderPendingFail(data: TradingList.TradeOrderPendingRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetOrderPending(body: TradingList.TradeOrderPendingObj, context: Context)
    }
}