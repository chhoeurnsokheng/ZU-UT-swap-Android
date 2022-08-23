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

    /** Create Trade Order*/
    @POST(ApiSettings.PATH_TRADE_CREATE_ORDER)
    fun createOrder(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeCreateOrderObj
    ):Observable<TradingList.TradeCreateOrderRes>

    /** Get Trade Order Pending List*/
    @POST(ApiSettings.PATH_GET_TRADE_ORDER_PENDING)
    fun getTradeOrderPending(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeOrderPendingObj
    ):Observable<TradingList.TradeOrderPendingRes>

    /** Matching Trade Transaction*/
    @POST(ApiSettings.PATH_TRADE_MATCHING_TRANSACTION)
    fun getTradeMatchingTransaction(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeMatchingTransactionObj
    ):Observable<TradingList.TradeMatchingTransactionRes>

    /** Get Available Balance*/
    @POST(ApiSettings.PATH_GET_AVAILABLE_BALANCE)
    fun getAvailableBalance(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.AvailableBalanceObj
    ): Observable<TradingList.AvailableBalanceRes>

    /** Trade Cancel Order*/
    @POST(ApiSettings.PATH_TRADE_CANCEL_ORDER)
    fun cancelTradeOrder(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeCancelOrderObj
    ):Observable<TradingList.TradeCancelOrderRes>

    /** Trade Chart*/
    @POST(ApiSettings.PATH_TRADE_CHART)
    fun tradeChart(
        @HeaderMap headerMap: Map<String,String>,
        @Body body: TradingList.TradeChartObj
    ):Observable<TradingList.TradeChartRes>
}