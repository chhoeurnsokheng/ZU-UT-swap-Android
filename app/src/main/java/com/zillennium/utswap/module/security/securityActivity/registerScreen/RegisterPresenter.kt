package com.zillennium.utswap.module.security.securityActivity.registerScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInView

class RegisterPresenter : BaseMvpPresenterImpl<RegisterView.View>(),
    RegisterView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}