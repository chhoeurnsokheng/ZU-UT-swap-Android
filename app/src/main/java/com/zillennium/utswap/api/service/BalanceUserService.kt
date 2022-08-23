package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface BalanceUserService {

    @GET(ApiSettings.PATH_FINANCE_USER_BALANCE)
    fun getUserBalance(
        @HeaderMap headers: Map<String, String>
    ): Observable<BalanceFinance.GetUserBalanceInfo>

    @POST(ApiSettings.PATH_FINANCE_ACCOUNT_BALANCE_SEARCH_DATA)
    fun getUserBalanceDateFilter(
        @HeaderMap headers: Map<String, String>,
        @Body body: BalanceFinance.GetBalanceSearchDateObject
    ): Observable<BalanceFinance.GetBalanceSearchDate>

    @POST(ApiSettings.PATH_FINANCE_ACCOUNT_BALANCE_EXPORT_PDF)
    fun getExportUserBalance(
        @HeaderMap headers: Map<String, String>,
        @Body body: BalanceFinance.ExportFinanceBalanceObject
    ): Observable<BalanceFinance.ExportFinanceBalance>
}