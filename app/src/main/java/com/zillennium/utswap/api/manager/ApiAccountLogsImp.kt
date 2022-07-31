package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.logs.Logs
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiAccountLogsImp : ApiManager() {
    fun accountLogs(
        body: Logs.AccountLogsObject,
        context: Context
    ): Observable<Logs.AccountLogsRes> =
        mAccountLogsService.accountLogs(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}