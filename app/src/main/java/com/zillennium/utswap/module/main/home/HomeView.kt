package com.zillennium.utswap.module.main.home

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.home.BannerObj

class HomeView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetBanner(data:BannerObj.Banner)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getBanner(context: Context)
    }
}