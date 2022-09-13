package com.zillennium.utswap.module.main.home

import android.content.Context
import android.os.Bundle
import android.os.Message
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.newsService.News

class HomeView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetBannerSuccess(data:BannerObj.Banner)
        fun onGetBannerFail(message: String)
        fun onGetNewsHomeSuccess(data: News.NewsRes)
        fun onGetNewsHomeFail(message: String)
        fun onGetNewsHomeNoTokenSuccess(data: News.NewsRes)
        fun onGetNewsHomeNoTokenFail(message: String)
        fun onGetWishListAndBalanceSuccess(data: BannerObj.whistListRes)
        fun onGetWishListAndBalanceFail(message: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getBanner(context: Context)
        fun getNewsHome(context: Context)
        fun getNewsWithoutToken(context: Context)
        fun getWatchListAndBalance(context: Context)
    }
}