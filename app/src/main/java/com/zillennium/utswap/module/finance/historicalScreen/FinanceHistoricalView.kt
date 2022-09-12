package com.zillennium.utswap.module.finance.historicalScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.financeHistorical.Historical

class FinanceHistoricalView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetMarketNameSuccess(data: List<Historical.GetMarketNameData>)
        fun onGetMarketNameFail(data: Historical.GetMarketName)
        fun onGetUserTransactionSuccess(data: Historical.UserTransactionData)
        fun onGetUserTransactionFail(data: Historical.UserTransaction)
        fun onGetTradeTransactionSuccess(data: Historical.TradeTransactionData)
        fun onGetTradeTransactionFail(data: Historical.TradeTransaction)
        fun onGetAllTransactionSuccess(data: Historical.AllTransactionData)
        fun onGetAllTransactionFail(data: Historical.AllTransaction)
        fun onExportHistoricalSuccess(data: Historical.DataExportHistorical)
        fun onExportHistoricalFail(data: Historical.exportHistorical)
        fun onUserExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetMarketName()
        fun onGetUserTransaction(body: Historical.UserTransactionObject, context:Context)
        fun onGetTradeTransaction(body: Historical.TradeTransactionObject, context: Context)
        fun onGetAllTransaction(body: Historical.AllTransactionObject, context: Context)
        fun onExportHistorical(body: Historical.exportHistoricalObject, context: Context)
    }
}