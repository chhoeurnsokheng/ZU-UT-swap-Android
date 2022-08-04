package com.zillennium.utswap.module.main.home

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiHomeScreenImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import rx.Subscription
import java.util.concurrent.Flow

class HomePresenter : BaseMvpPresenterImpl<HomeView.View>(),
    HomeView.Presenter {

    var subscription: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getBanner(context: Context) {
        subscription?.unsubscribe()
        subscription = ApiHomeScreenImp().getBanner(context).subscribe({
            mView?.onGetBanner(it)
        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data)
                }
            }
        })
    }
}