package com.zillennium.utswap.module.security.securityFragment.changeLoginPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class ChangeLoginPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun onChangePasswordSuccess(data: User.ChangeLoginPasswordRes)
        fun onChangePasswordFail(data: User.ChangeLoginPasswordRes)
        fun onUserExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onChangeLoginPassword(body: User.ChangeLoginPasswordObject, context: Context)
    }
}