package com.zillennium.utswap.screens.navbar.navbar

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NavbarPresenter : BaseMvpPresenterImpl<NavbarView.View>(),
        NavbarView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}