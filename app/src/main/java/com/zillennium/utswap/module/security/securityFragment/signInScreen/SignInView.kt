package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.user.LoginVerifyLoginParam
import com.zillennium.utswap.models.user.VerifiyCode

class SignInView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetVerifySuccess(data: VerifiyCode)
        fun onGetVerifyFail()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun postTogetVerifyCode(context: Context, param: LoginVerifyLoginParam)
    }
}