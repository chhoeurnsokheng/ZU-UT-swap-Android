package com.zillennium.utswap.module.main.trade.tradeScreen

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class TradeView {
    interface View : BaseMvpView {
        override fun initView()
        //fun fetchTradingDataSuccess(data: String)
        var fetchTradeData: MutableLiveData<TradingList.TradingListRes>
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun startSocketTrading()
        fun closeSocketTrading()
    }
}