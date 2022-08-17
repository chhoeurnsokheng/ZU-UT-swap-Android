package com.zillennium.utswap.module.main

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiHomeImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import rx.Subscription

class MainPresenter : BaseMvpPresenterImpl<MainView.View>(),
    MainView.Presenter {
    var forceUpdateSubscription: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun checkForceUpdate(context: Context) {
        forceUpdateSubscription?.unsubscribe()
        forceUpdateSubscription = ApiHomeImp().checkForceUpdate(context).subscribe({
            mView?.onGetForceUpdateSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, context.applicationContext, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onGetForceUpdateFailed("Failed")
                }

            }
        })
    }
}