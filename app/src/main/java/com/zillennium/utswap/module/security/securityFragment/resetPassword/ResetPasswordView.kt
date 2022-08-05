package com.zillennium.utswap.module.security.securityFragment.resetPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class ResetPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun onResetSuccess(body: User.ForgotPasswordRes)
        fun onResetFail(body: User.ForgotPasswordRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onResetPassword(body: User.ForgotPasswordObject,context: Context)
    }
}