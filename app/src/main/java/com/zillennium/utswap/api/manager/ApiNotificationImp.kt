package com.zillennium.utswap.api.manager

import android.content.Context
import com.google.gson.JsonObject
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.BaseResponse.BaseResponse
import com.zillennium.utswap.models.notification.NotificationModel
import retrofit2.http.Body
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ApiNotificationImp : ApiManager() {
    fun notification(context: Context, page: JsonObject): Observable<NotificationModel.NotificationRes> =
        mNotificationService.notificationList(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context), page

            ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun onReadNotification(
        context: Context,
        body: JsonObject
    ): Observable<JsonObject> = mNotificationService.onReadNotification(
        Header.getHeader(
            Header.Companion.AuthType.REQUIRED_TOKEN,
            context
        ), body
    )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun onReadAllNotification(context: Context): Observable<JsonObject> =
        mNotificationService.onReadAllNotification(
            Header.getHeader(
                Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveFirebaseToken(context: Context, body: NotificationModel.SubmitFirebaseToken): Observable<JsonObject> =
        mNotificationService.saveFirebaseToken(
            Header.getHeader(
                Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            ), body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}