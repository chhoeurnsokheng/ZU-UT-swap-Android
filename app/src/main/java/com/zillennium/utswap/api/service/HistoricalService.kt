package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.financeHistorical.Historical
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface HistoricalService {

    @GET (ApiSettings.PATH_GET_MARKET_NAME)
    fun getMarketName(): Observable<Historical.GetMarketName>

    @POST(ApiSettings.PATH_USER_TRANSACTION)
    fun getUserTransaction(
        @HeaderMap headers: Map<String, String>,
        @Body body: Historical.UserTransactionObject
    ): Observable<Historical.UserTransaction>

    @POST(ApiSettings.PATH_TRADE_HISTORY)
    fun getTradeTransaction(
        @HeaderMap header: Map<String, String>,
        @Body body: Historical.TradeTransactionObject
    ): Observable<Historical.TradeTransaction>

    @POST(ApiSettings.PATH_ALL_TRANSACTION)
    fun getAllTransaction(
        @HeaderMap headers: Map<String, String>,
        @Body body: Historical.AllTransactionObject
    ): Observable<Historical.AllTransaction>

    @POST(ApiSettings.PATH_EXPORT_HISTORICAL_DATA)
    fun getExportHistorical(
        @HeaderMap headers: Map<String, String>,
        @Body body: Historical.exportHistoricalObject
    ): Observable<Historical.exportHistorical>
}