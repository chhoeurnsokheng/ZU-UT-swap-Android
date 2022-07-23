package com.zillennium.utswap.module.security.securityFragment.verificationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.data.api.model.userService.User

class VerificationView {
    interface View : BaseMvpView {
        override fun initView()
        override fun onFail(any: Any)
        fun otpSuccess(body: User.OtpRes)
        fun otpFail(body: User.OtpRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun otpVerification(body: User.OtpObject, context: Context)
    }
}