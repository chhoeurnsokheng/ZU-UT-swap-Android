package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.tradingList.TradingList
import retrofit2.http.GET
import rx.Observable

interface TradeService {
    @GET(ApiSettings.PATH_UPCOMING_PROJECT_TRADING_LIST)
    fun getUpcomingProject(): Observable<TradingList.TradeUpComingProjectRes>
}