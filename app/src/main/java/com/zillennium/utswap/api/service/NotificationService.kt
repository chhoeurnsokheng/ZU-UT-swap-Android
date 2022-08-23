package com.zillennium.utswap.api.service

import com.google.gson.JsonObject
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.notification.NotificationModel
import retrofit2.http.*
import rx.Observable

interface NotificationService {
    @GET(ApiSettings.PATH_NOTIFICATION_LIST)
    fun notificationList(
        @HeaderMap headers: Map<String, String>,
        @Query ("page") page: Int
    ): Observable<NotificationModel.NotificationRes>

    @POST(ApiSettings.PATH_NOTIFICATION_READ)
    fun onReadNotification(
        @HeaderMap headerMap: Map<String, String>,
        @Body body: JsonObject
    ):Observable<JsonObject>

    @POST(ApiSettings.PATH_NOTIFICATION_READ_ALL)
    fun onReadAllNotification(
        @HeaderMap headers: Map<String, String>
    ): Observable<JsonObject>


}