package com.zillennium.utswap.module.main

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.models.userService.User

class MainView {
    interface View : BaseMvpView {
        override fun initView()
        fun onCheckKYCSuccess(data: User.KycRes)
        fun onCheckKYCFail()
        fun onNotificationSuccess(data: NotificationModel.NotificationData)
        fun onNotificationFail(data: NotificationModel.NotificationRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onCheckKYCStatus()
        fun getNotificationLists(context: Context)

    }
}