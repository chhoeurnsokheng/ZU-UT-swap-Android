package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.chart

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class ChartView {
    interface View : BaseMvpView {
        override fun initView()
        fun getTradeChartSuccess(data: TradingList.TradeChartRes)
        fun getTradeChartFail(data: TradingList.TradeChartRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getTradeChart(body: TradingList.TradeChartObj, context: Context)
    }
}