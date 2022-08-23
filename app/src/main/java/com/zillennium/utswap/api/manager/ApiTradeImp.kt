package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.tradingList.TradingList
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiTradeImp: ApiManager() {
    fun upcomingProject(): Observable<TradingList.TradeUpComingProjectRes> =
        mTradeService.getUpcomingProject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getFavoriteProject(body: TradingList.TradeFavoriteProjectObj, context: Context): Observable<TradingList.TradeFavoriteProjectRes> =
        mTradeService.getFavoriteProject(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addFavoriteProject(body: TradingList.TradeAddFavoriteObj, context: Context): Observable<TradingList.TradeAddFavoriteRes> =
        mTradeService.addFavoriteProject(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun createOrder(body: TradingList.TradeCreateOrderObj, context: Context): Observable<TradingList.TradeCreateOrderRes> =
        mTradeService.createOrder(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Get Trade Order Pending List*/
    fun getTradeOrderPending(body: TradingList.TradeOrderPendingObj, context: Context): Observable<TradingList.TradeOrderPendingRes> =
        mTradeService.getTradeOrderPending(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Matching Trade Transaction*/
    fun getTradeMatchingTransaction(body: TradingList.TradeMatchingTransactionObj, context: Context): Observable<TradingList.TradeMatchingTransactionRes> =
        mTradeService.getTradeMatchingTransaction(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Get Available Balance*/
    fun getAvailableBalance(body:TradingList.AvailableBalanceObj, context: Context):Observable<TradingList.AvailableBalanceRes> =
        mTradeService.getAvailableBalance(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Trade Cancel Order*/
    fun cancelTradeOrder(body:TradingList.TradeCancelOrderObj, context: Context):Observable<TradingList.TradeCancelOrderRes> =
        mTradeService.cancelTradeOrder(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Trade Chart*/
    fun tradeChart(body:TradingList.TradeChartObj, context: Context): Observable<TradingList.TradeChartRes> =
        mTradeService.tradeChart(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}