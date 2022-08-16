package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.portfolio.PortfolioFilterObj
import com.zillennium.utswap.models.portfolio.PortfolioGraphObj
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Sokheng Chhoeurn on 15/8/22.
 * Build in Mac
 */
interface PortfolioService {
    @GET(ApiSettings.PATH_FINANCE_PORTFOLIOl_GRAPH)
    fun getPortfolioGraph(
        @HeaderMap header: Map<String,String>,
    ):Observable<PortfolioGraphObj>
    @POST(ApiSettings.PATH_GET_FINANCE_FILTER_TYPE)
    fun getPortfolioFilter(
        @HeaderMap header: Map<String, String>,
        @Query("type") type:Int
    ):Observable<PortfolioFilterObj>

}