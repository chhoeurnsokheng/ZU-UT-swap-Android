package com.zillennium.utswap.module.security.securityFragment.verificationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class VerificationView {
    interface View : BaseMvpView {
        override fun initView()
        override fun onFail(any: Any)
        fun otpSuccess(body: User.OtpRes)
        fun otpFail(body: User.OtpRes)
        fun onResendCodeSuccess(data: User.RegisterRes)
        fun onResendCodeFail(data: User.RegisterRes)
        fun onResetPasswordSuccess(data: User.ForgotPasswordVerifyRes)
        fun onResetPasswordFail(data: User.ForgotPasswordVerifyRes)
        fun onResendCodeResetPasswordSuccess(data: User.ForgotPasswordRes)
        fun onResendCodeResetPasswordFail(data: User.ForgotPasswordRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun otpVerification(body: User.OtpObject, context: Context)
        fun onResendCode(data: User.RegisterObject)
        fun onResendCodeResetPassword(data: User.ForgotPasswordObject,context: Context)
        fun onResetPassword(data: User.ForgotPasswordVerifyObject,context: Context)
    }
}