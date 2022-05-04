package com.zillennium.utswap.screens.system.welcomeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class WelcomePresenter : BaseMvpPresenterImpl<WelcomeView.View>(),
    WelcomeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}