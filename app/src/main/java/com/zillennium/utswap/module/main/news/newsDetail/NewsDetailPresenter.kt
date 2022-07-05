package com.zillennium.utswap.module.main.news.newsDetail

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NewsDetailPresenter : BaseMvpPresenterImpl<NewsDetailView.View>(),
    NewsDetailView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}