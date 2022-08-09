package com.zillennium.utswap.module.main.home

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.newsService.News

class HomeView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetBanner(data:BannerObj.Banner)
        fun onGetNEwsHome(data: News.NewsRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getBanner(context: Context)
        fun getNewsHome(context: Context)
    }
}