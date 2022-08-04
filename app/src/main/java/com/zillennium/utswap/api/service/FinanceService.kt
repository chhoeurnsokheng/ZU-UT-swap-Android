package com.zillennium.utswap.api.service

import com.google.gson.JsonObject
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface FinanceService {

    @POST(ApiSettings.PATH_SUBSCRIPTION)
    fun postSubscription (
        @HeaderMap headerMap: Map<String, String>,
        @Body body: SubscriptionObject.SubscriptionBody
    ): Observable<JsonObject>

    @POST(ApiSettings.PATH_LOCK_UP)
    fun postLockUpBalance(
        @HeaderMap headerMap: Map<String, String>,
        @Body body: JsonObject
    ): Observable<JsonObject>


}