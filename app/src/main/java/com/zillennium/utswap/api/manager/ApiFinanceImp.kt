package com.zillennium.utswap.api.manager

import android.content.Context
import com.google.gson.JsonObject
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import org.json.JSONObject
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiFinanceImp : ApiManager() {

    fun postSubscription(
        context: Context,
        body: SubscriptionObject.SubscriptionBody
    ): Observable<JsonObject> =
        mFinanceService.postSubscription(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context), body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun postLockUpBalance(
        context: Context,
        type: JsonObject
    ): Observable<JsonObject> = mFinanceService.postLockUpBalance(
        Header.getHeader(
            Header.Companion.AuthType.REQUIRED_TOKEN,
            context
        ), type
    )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


}