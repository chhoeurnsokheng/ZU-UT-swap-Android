package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.logs.Logs
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface AccountLogsService {
    @POST(ApiSettings.PATH_ACCOUNT_LOGS)
    fun accountLogs(
        @HeaderMap header: Map<String, String>,
        @Body body: Logs.AccountLogsObject
    ): Observable<Logs.AccountLogsRes>
}