package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.user.LoginVerifyLoginParam
import rx.Subscription

class SignInPresenter : BaseMvpPresenterImpl<SignInView.View>(),
    SignInView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    private var onGetVerifySuccess: Subscription? = null
    private var onGetVerifyField: Subscription? = null
    override fun onUnSubscript() {
        super.onUnSubscript()
        onGetVerifyField?.unsubscribe()
    }

    override fun postTogetVerifyCode(context: Context, param: LoginVerifyLoginParam) {
        mContext.let {
            onGetVerifySuccess?.unsubscribe()
            onGetVerifySuccess = ApiUserImp().loginVerifyCode(context, param).subscribe({ respone ->
                mView?.onGetVerifySuccess(respone)

            }, { error ->
                object : CallbackWrapper(error, context, arrayListOf()) {
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.onGetVerifyFail()
                    }
                }

            })
        }
    }

}