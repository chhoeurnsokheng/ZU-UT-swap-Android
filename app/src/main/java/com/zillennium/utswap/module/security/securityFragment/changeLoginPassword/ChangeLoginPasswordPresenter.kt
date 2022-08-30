package com.zillennium.utswap.module.security.securityFragment.changeLoginPassword

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class ChangeLoginPasswordPresenter : BaseMvpPresenterImpl<ChangeLoginPasswordView.View>(),
    ChangeLoginPasswordView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onChangeLoginPassword(body: User.ChangeLoginPasswordObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().changeLoginPassword(body,context).subscribe({
            if(it.status == 1){
                mView?.onChangePasswordSuccess(it)
            }else{
                mView?.onChangePasswordFail(it)
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