package com.zillennium.utswap.module.security.securityFragment.resetPassword

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class ResetPasswordPresenter : BaseMvpPresenterImpl<ResetPasswordView.View>(),
    ResetPasswordView.Presenter {

    private var subscriptions: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onResetPassword(body: User.ForgotPasswordObject, context: Context) {
        subscriptions?.unsubscribe()
        subscriptions = ApiUserImp().resetPassword(body,context).subscribe({
            if(it.status == 1)
            {
                mView?.onResetSuccess(it)
            }else{
                mView?.onResetFail(it)
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