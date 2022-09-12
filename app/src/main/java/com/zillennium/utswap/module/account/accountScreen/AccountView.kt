package com.zillennium.utswap.module.account.accountScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class AccountView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetUserInfoSuccess(data: User.AppSideBarData)
        fun onGetUserInfoFail(data: User.AppSideBarData)
        fun uploadProfileSuccess(data: User.AccountUploadProfileRes)
        fun uploadProfileFail(data: User.AccountUploadProfileRes)
        fun userExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetUserInfo(context: Context)
        fun uploadProfile(body: User.AccountUploadProfileObject, context: Context)
    }
}