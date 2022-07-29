package com.zillennium.utswap.module.main.news.newsDetail

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.newsService.News

class NewsDetailView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetNewsSuccess(data: News.NewsDetailData)
        fun onGetNewsFail(data: News.NewsDetailData)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetNewsDetail(id: String)
    }
}