package com.zillennium.utswap.module.security.securityFragment.verificationScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class VerificationPresenter : BaseMvpPresenterImpl<VerificationView.View>(),
    VerificationView.Presenter {

    private var subscription: Subscription? = null
    private var subscriptionResendCode: Subscription? = null
    private var subscriptionResetPassword: Subscription? = null
    private var subscriptionResendCodeReset: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun otpVerification(body: User.OtpObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().otp(body,context).subscribe({
            if(it.status == 1){
                mView?.otpSuccess(it)
            }else{
                mView?.otpFail(it)
            }
        },{
            object : CallbackWrapper(it, context, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onResendCode(data: User.RegisterObject) {
        subscriptionResendCode?.unsubscribe()
        subscriptionResendCode = ApiUserImp().register(data).subscribe({
            if(it.status == 1){
                mView?.onResendCodeSuccess(it)
            }else{
                mView?.onResendCodeFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onResendCodeResetPassword(data: User.ForgotPasswordObject, context: Context) {
        subscriptionResendCodeReset?.unsubscribe()
        subscriptionResendCodeReset = ApiUserImp().resetPassword(data,context).subscribe({
            if(it.status == 1){
                mView?.onResendCodeResetPasswordSuccess(it)
            }else{
                mView?.onResendCodeResetPasswordFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onResetPassword(data: User.ForgotPasswordVerifyObject,context: Context) {
        subscriptionResetPassword?.unsubscribe()
        subscriptionResetPassword = ApiUserImp().resetPasswordVerify(data,context).subscribe({
            if(it.status == 1){
                mView?.onResetPasswordSuccess(it)
            }else{
                mView?.onResetPasswordFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onVerifyAddPhoneNumber(data: User.VerifyAddPhoneNumberObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().verifyAddPhoneNumber(data,context).subscribe({
            if(it.status == 1){
                mView?.onVerifyAddPhoneNumberSuccess(it)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onVerifyAddPhoneNumberFail(it)
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onResendCodeAddPhoneNumber(data: User.AddPhoneNumberObject, context: Context) {
        subscriptionResendCode?.unsubscribe()
        subscriptionResendCode = ApiUserImp().addPhoneNumber(data,context).subscribe({
            if(it.status == 1)
            {
                mView?.onResendCodeAddPhoneSuccess(it)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onResendCodeAddPhoneFail(it)
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
}