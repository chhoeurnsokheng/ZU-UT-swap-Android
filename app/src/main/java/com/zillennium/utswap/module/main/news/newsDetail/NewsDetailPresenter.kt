package com.zillennium.utswap.module.main.news.newsDetail

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNewsImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import rx.Subscription

class NewsDetailPresenter : BaseMvpPresenterImpl<NewsDetailView.View>(),
    NewsDetailView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetNewsDetail(id: String) {
        subscription?.unsubscribe()
        subscription = ApiNewsImp().getNewsDetail(id).subscribe({
            if(it.status == 1){
                mView?.onGetNewsSuccess(it.data!!)
            }else{
                mView?.onGetNewsFail(it.data!!)
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
