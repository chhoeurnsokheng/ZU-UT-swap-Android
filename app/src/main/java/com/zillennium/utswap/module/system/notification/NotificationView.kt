package com.zillennium.utswap.module.system.notification

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.notification.NotificationModel

class NotificationView {
    interface View : BaseMvpView {
        override fun initView()
        fun onNotificationSuccess(data: NotificationModel.NotificationData)
        fun onNotificationFail(data: NotificationModel.NotificationRes)
        fun onReadNotificationSuccess(message: String)
        fun onReadNotificationFail()
        fun onReadAllNotificationSuccess(message: String)
        fun onReadAllNotificationFail()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getNotificationLists(context: Context, page: Int)
        fun readNotification(id: String)
        fun readAllNotification()
    }
}