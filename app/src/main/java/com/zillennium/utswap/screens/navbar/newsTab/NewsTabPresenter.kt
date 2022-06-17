package com.zillennium.utswap.screens.navbar.newsTab

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NewsTabPresenter: BaseMvpPresenterImpl<NewsTabView.View>(),
    NewsTabView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}