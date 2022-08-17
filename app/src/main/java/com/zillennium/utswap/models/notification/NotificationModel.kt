package com.zillennium.utswap.models.notification

import java.io.Serializable

class NotificationModel(
    var icNotification: Int,
    var txtTitle: String,
    var txtTitleAnnouncement: String,
    var txtDescription: String,
    var txtDuration: String
) :
    Serializable