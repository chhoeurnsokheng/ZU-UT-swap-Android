package com.zillennium.utswap.api.manager

import com.zillennium.utswap.models.tradingList.TradingList
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiTradeImp: ApiManager() {
    fun upcomingProject(): Observable<TradingList.TradeUpComingProjectRes> =
        mTradeService.getUpcomingProject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}