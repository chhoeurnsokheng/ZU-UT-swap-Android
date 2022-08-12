package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.customerSupport.CustomerSupport
import retrofit2.http.GET
import rx.Observable

interface CustomerSupportService {
    @GET(ApiSettings.PATH_CUSTOMER_SUPPORT)
    fun customerSupport(): Observable<CustomerSupport.CustomerSupportRes>
}