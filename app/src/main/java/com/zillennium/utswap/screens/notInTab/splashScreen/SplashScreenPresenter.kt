package com.zillennium.utswap.screens.notInTab.splashScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SplashScreenPresenter : BaseMvpPresenterImpl<SplashScreenView.View>(),
    SplashScreenView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}