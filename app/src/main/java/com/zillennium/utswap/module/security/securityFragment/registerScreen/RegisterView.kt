package com.zillennium.utswap.module.security.securityFragment.registerScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class RegisterView {
    interface View : BaseMvpView {
        override fun initView()
        fun onRegisterSuccess(data: User.RegisterRes)
        fun onRegisterFail(data: User.RegisterRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onSubmitRegister(data: User.RegisterObject)
    }
}