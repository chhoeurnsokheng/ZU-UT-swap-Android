package com.zillennium.utswap.module.project.subscriptionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.models.userService.User

class SubscriptionView {
    interface View : BaseMvpView {
        override fun initView()
        fun onCheckKYCSuccess(data: User.KycRes)
        fun onCheckKYCFail()

        /**   Subscription Project   **/
        fun onCheckSubscriptionSuccess(data: SubscriptionProject.SubscriptionProjectRes)
        fun onCheckSubscriptionFail(data: SubscriptionProject.SubscriptionProjectRes)

        /**   User Profile Level      **/
        fun onGetUserInfoSuccess(data: User.AppSideBarData)
        fun onGetUserInfoFail(data: User.AppSideBarData)

        fun userExpiredToken()


    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onCheckKYCStatus()

        /**   Subscription Project   **/
        fun onCheckSubscriptionStatus(body: SubscriptionProject.SubscriptionProjectBody, context: Context)

        /**  User Profile Level   **/
        fun onGetUserInfo(context: Context)


    }
}