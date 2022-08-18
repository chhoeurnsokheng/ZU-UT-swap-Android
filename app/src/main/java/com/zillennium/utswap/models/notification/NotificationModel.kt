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
    }

    class NotificationListData(
        var title: String? = "",
        var id: String? = null,
        var to_userid: String? = null,
        var body: String? = null,
        var sent_time: String? = null,
        var icon: String? = null,
        var action: String? = null,
        var mark_as_read: String? = null,
        var action_title: String? = null,
        var mark_as_read_msg: String? = null

    )
}
//
//class NotificationModel(
//    var icNotification: Int,
//    var txtTitle: String,
//    var txtTitleAnnouncement: String,
//    var txtDescription: String,
//    var txtDuration: String
//) :
//    Serializable