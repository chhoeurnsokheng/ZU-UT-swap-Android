package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.tradingList.TradingList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface TradeService {
    @GET(ApiSettings.PATH_UPCOMING_PROJECT_TRADING_LIST)
    fun getUpcomingProject(): Observable<TradingList.TradeUpComingProjectRes>

    @POST(ApiSettings.PATH_CHECK_FAVORITE_PROJECT_TRADE)
    fun getFavoriteProject(
        @HeaderMap headers: Map<String, String>,
        @Body body: TradingList.TradeFavoriteProjectObj
    ): Observable<TradingList.TradeFavoriteProjectRes>

    @POST(ApiSettings.PATH_ADD_FAVORITE_PROJECT)
    fun addFavoriteProject(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeAddFavoriteObj
    ): Observable<TradingList.TradeAddFavoriteRes>

    @POST(ApiSettings.PATH_TRADE_CREATE_ORDER)
    fun createOrder(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeCreateOrderObj
    ):Observable<TradingList.TradeCreateOrderRes>
}