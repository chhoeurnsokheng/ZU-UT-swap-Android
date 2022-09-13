package com.zillennium.utswap.module.main.news

import android.content.Context
import android.os.Bundle
import android.telecom.Call
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.JsonObject
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNewsImp
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.newsService.News
import rx.Subscription

class NewsPresenter : BaseMvpPresenterImpl<NewsView.View>(),
    NewsView.Presenter {

    private var subscription: Subscription? = null
    private var subscriptionGetNewNoToken: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetNews(context: Context, body: News.NewsObj) {
        subscription?.unsubscribe()
        subscription = ApiNewsImp().getNews(context, body).subscribe({
            if (it.status == 1) {
                it.data?.let { res ->
                    mView?.onGetNewsSuccess(res)
                }
            } else {
                mView?.onGetNewsFail()
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun getNewsWithoutToken(page: Int) {
        val param = JsonObject()
        param.addProperty("page", page)
        subscriptionGetNewNoToken?.unsubscribe()
        subscription = ApiNewsImp().getNewsHomeNoToken(param).subscribe({
            if (it.status == 1) {
                it.data?.let { res ->
                    mView?.onGetNewsSuccess(res)
                }
            } else {
                mView?.onGetNewsFail()
            }
        }, {
            object : CallbackWrapper(it, mContext!!, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data)
                }
            }

        })
    }
}