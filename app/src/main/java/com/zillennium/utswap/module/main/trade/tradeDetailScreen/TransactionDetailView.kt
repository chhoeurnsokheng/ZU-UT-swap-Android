package com.zillennium.utswap.module.main.trade.tradeDetailScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class TransactionDetailView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetTransactionDetailSuccess(data: TradingList.TradeTransactionDetailData)
        fun onGetTransactionDetailFail(data: TradingList.TradeTransactionDetailData)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetTransactionDetail(body: TradingList.TradeTransactionDetailObj, context: Context)
    }
}