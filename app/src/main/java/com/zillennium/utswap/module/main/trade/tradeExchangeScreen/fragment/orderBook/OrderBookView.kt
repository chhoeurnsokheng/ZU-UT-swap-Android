package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class OrderBookView {
    interface View : BaseMvpView {
        override fun initView()
        var fetchTradeOrderBookTable: MutableLiveData<TradingList.TradeOrderBookTableRes>
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun startTradeOrderBookTable(marketName: String?)
        fun closeTradeOrderBookTable()
    }
}