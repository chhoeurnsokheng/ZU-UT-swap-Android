package com.zillennium.utswap.module.security.securityFragment.newPasswordScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class NewPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun onChangePasswordSuccess(body: User.EnterNewPasswordRes)
        fun onChangePasswordFail(body: User.EnterNewPasswordRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onEnterNewPassword(body: User.EnterNewPasswordObject,context: Context)
    }
}