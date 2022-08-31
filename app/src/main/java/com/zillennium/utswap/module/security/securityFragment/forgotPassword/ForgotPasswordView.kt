package com.zillennium.utswap.module.security.securityFragment.forgotPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class ForgotPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun onForgotLoginPasswordSuccess(data: User.ForgotPasswordRes)
        fun onForgotLoginPasswordFail(data: User.ForgotPasswordRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun forgotPassword(body: User.ForgotPasswordObject, context: Context)
    }
}