package com.zillennium.utswap.module.main.home

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiHomeImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNewsImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import rx.Subscription

class HomePresenter : BaseMvpPresenterImpl<HomeView.View>(),
    HomeView.Presenter {

    var subscription: Subscription? = null
    var subscriptionGetBanner: Subscription? = null
    var subscriptionGetWishListBalance: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getBanner(context: Context) {
        subscriptionGetBanner?.unsubscribe()
        subscriptionGetBanner = ApiHomeImp().getBanner(context).subscribe({
            mView?.onGetBannerSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onGetBannerFail("Failed")
                }
            }
        })
    }

    override fun getNewsHome(context: Context) {
       subscription?.unsubscribe()
        subscription = ApiNewsImp().getNewsHome(context).subscribe({
            mView?.onGetNewsHomeSuccess(it)
        },{ error ->
            object :CallbackWrapper(error,UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onGetNewsHomeFail("Failed")
                }

            }
        })
    }

    override fun getWishListAndBalance(context: Context) {
        subscriptionGetWishListBalance?.unsubscribe()
        subscriptionGetWishListBalance = ApiHomeImp().getWishListAndBalance(context).subscribe({
            mView?.onGetWishListAndBalanceSuccess(it)
        },{error -> object :CallbackWrapper(error,UTSwapApp.instance, arrayListOf()){
            override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                mView?.onGetWishListAndBalanceFail("Failed")
            }
        }
        })
    }
}