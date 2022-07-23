package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.data.api.model.userService.User

class SignInView {
    interface View : BaseMvpView {
        override fun initView()
        fun loginSuccess(body: User.LoginRes)
        fun loginFail(body: User.LoginRes)
        override fun onFail(any: Any)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun login(body: User.LoginObject, context: Context)
    }
}