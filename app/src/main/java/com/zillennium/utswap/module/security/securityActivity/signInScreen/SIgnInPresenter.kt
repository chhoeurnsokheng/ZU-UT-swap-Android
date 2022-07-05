package com.zillennium.utswap.module.security.securityActivity.signInScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SIgnInPresenter : BaseMvpPresenterImpl<SignInView.View>(),
    SignInView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}