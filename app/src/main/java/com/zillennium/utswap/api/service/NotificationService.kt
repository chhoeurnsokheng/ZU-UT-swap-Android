package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.notification.NotificationModel
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import rx.Notification
import rx.Observable

interface NotificationService {
    @GET(ApiSettings.PATH_NOTIFICATION_LIST)
    fun notificationList(
        @HeaderMap headers: Map<String, String>
    ): Observable<NotificationModel.NotificationRes>
}