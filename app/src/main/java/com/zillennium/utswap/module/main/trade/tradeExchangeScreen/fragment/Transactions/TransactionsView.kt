package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.tradingList.TradingList

class TransactionsView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetMatchingTransactionSuccess(data: TradingList.TradeMatchingTransactionRes)
        fun onGetMatchingTransactionFail(data: TradingList.TradeMatchingTransactionRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetMatchingTransaction(body: TradingList.TradeMatchingTransactionObj, context: Context)
    }
}