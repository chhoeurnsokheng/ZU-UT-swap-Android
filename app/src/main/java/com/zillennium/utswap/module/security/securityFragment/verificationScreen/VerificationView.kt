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

        fun onVerifyAddPhoneNumberSuccess(data:User.VerifyAddPhoneNumberRes)
        fun onVerifyAddPhoneNumberFail(data:User.VerifyAddPhoneNumberRes)

        fun onResendCodeAddPhoneSuccess(data: User.AddPhoneNumberRes)
        fun onResendCodeAddPhoneFail(data: User.AddPhoneNumberRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun otpVerification(body: User.OtpObject, context: Context)
        fun onResendCode(data: User.RegisterObject)
        fun onResendCodeResetPassword(data: User.ForgotPasswordObject,context: Context)
        fun onResetPassword(data: User.ForgotPasswordVerifyObject,context: Context)
        fun onVerifyAddPhoneNumber(data:User.VerifyAddPhoneNumberObject, context: Context)
        fun onResendCodeAddPhoneNumber(data: User.AddPhoneNumberObject,context: Context)
    }
}