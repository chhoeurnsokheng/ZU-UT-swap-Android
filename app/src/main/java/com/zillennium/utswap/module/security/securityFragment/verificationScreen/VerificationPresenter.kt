package com.zillennium.utswap.module.security.securityFragment.verificationScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.data.api.manager.ApiManager
import com.zillennium.utswap.data.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.data.api.model.userService.User
import rx.Subscription

class VerificationPresenter : BaseMvpPresenterImpl<VerificationView.View>(),
    VerificationView.Presenter {

    private var subscription: Subscription? = null

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
            object : CallbackWrapper(it, UTSwapApp.instance){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
}