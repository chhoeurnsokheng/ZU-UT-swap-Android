package com.zillennium.utswap.models.notification

import com.zillennium.utswap.models.project.ProjectList

object NotificationModel {
    class NotificationRes {
        var status: Int? = null
        var message: String? = null
        var data: NotificationData? = null
    }

    class NotificationData {
        var countGroupNoti: String? = null
        var list: ArrayList<NotificationListData>? = null
        var total_page: Int = 0
    }

    class NotificationListData(
        var notifi_type: String = "",
        var title: String? = "",
        var id: String? = null,
        var to_userid: String? = null,
        var body: String? = null,
        var sent_time: String? = null,
        var icon: String? = null,
        var action: String? = null,
        var mark_as_read: String? = null,
        var action_title: String? = null,
        var action_type: String = "",
        var mark_as_read_msg: String? = null,
        var project_id: String = "",
        var article_id: String = ""

    )

    class SubmitFirebaseToken {
        var firebase_client_token: String = ""
        var ip_device: String = ""
        var device_info: String = ""
    }

    data class NotificationSection(var title: String?)
}




