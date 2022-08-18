package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.financeHistorical.Historical
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiHistoricalImp: ApiManager() {

    fun getMarketName(): Observable<Historical.GetMarketName> =
        mHistorical.getMarketName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun userTransaction(body: Historical.UserTransactionObject, context: Context): Observable<Historical.UserTransaction> =
        mHistorical.getUserTransaction(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun tradeTransaction(body: Historical.TradeTransactionObject, context: Context) : Observable<Historical.TradeTransaction> =
        mHistorical.getTradeTransaction(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun allTransaction(body: Historical.AllTransactionObject, context: Context): Observable<Historical.AllTransaction> =
        mHistorical.getAllTransaction(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun exportHistorical(body: Historical.exportHistoricalObject, context: Context) : Observable<Historical.exportHistorical> =
        mHistorical.getExportHistorical(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
