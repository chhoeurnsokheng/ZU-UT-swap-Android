package com.zillennium.utswap.screens.navbar.homeTab

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class HomePresenter : BaseMvpPresenterImpl<HomeView.View>(),
    HomeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}