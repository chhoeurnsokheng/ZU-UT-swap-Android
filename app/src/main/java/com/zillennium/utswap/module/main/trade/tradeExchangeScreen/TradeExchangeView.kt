package com.zillennium.utswap.module.main.trade.tradeExchangeScreen

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class TradeExchangeView {
    interface View : BaseMvpView {
        override fun initView()
        var fetchTradeDetailData: MutableLiveData<TradingList.TradingListSummary>
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun startTradeDetailSocket(marketName: String?)
        fun closeTradeDetailSocket()
    }
}