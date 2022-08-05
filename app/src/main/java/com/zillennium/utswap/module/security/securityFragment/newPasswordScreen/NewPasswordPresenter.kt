package com.zillennium.utswap.module.security.securityFragment.newPasswordScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class NewPasswordPresenter : BaseMvpPresenterImpl<NewPasswordView.View>(),
    NewPasswordView.Presenter {

    private var subscriptionsNewPassword: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onEnterNewPassword(body: User.EnterNewPasswordObject, context: Context) {
        subscriptionsNewPassword?.unsubscribe()
        subscriptionsNewPassword = ApiUserImp().enterNewPassword(body,context).subscribe({
            if(it.status == 1)
            {
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