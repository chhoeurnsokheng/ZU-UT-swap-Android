package com.zillennium.utswap.module.main.news

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.newsService.News

class NewsView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetNewsSuccess(data: News.NewsData)
        fun onGetNewsFail(data: News.NewsData)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetNews(context: Context,body: News.NewsObj)
    }
}