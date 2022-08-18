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
}