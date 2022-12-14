package com.zillennium.utswap.api.service

import com.google.gson.JsonObject
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.notification.NotificationModel
import retrofit2.http.*
import rx.Observable

interface NotificationService {
    @POST(ApiSettings.PATH_NOTIFICATION_LIST)
    fun notificationList(
        @HeaderMap headers: Map<String, String>,
        @Body body: JsonObject
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

    @POST(ApiSettings.PATH_SAVE_FIREBASE_TOKEN)
    fun saveFirebaseToken(
        @HeaderMap header: Map<String, String>,
        @Body body: NotificationModel.SubmitFirebaseToken
    ): Observable<JsonObject>
}