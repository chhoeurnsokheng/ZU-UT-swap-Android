package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.notification.NotificationModel
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ApiNotificationImp : ApiManager() {
    fun notification(context: Context): Observable<NotificationModel.NotificationRes> =
        mNotificationService.notificationList(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),

            ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}