package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.portfolio.Portfolio
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface PortfolioService {

    @POST(ApiSettings.PATH_PORTFOLIO)
    fun getPortfolio(
        @HeaderMap headers: Map<String, String>,
        @Body body: Portfolio.GetPortfolioObject
    ): Observable<Portfolio.GetPortfolio>
}