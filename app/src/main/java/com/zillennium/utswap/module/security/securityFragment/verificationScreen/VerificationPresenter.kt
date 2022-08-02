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
}