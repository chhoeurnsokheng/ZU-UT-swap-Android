package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class SignInPresenter : BaseMvpPresenterImpl<SignInView.View>(),
    SignInView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun login(body: User.LoginObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().login(body, context).subscribe({
            if(it.status == 1){
                mView?.loginSuccess(it)
            }else{
                mView?.loginFail(it)
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