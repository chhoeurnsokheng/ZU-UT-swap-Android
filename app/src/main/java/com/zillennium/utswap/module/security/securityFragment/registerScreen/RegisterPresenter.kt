package com.zillennium.utswap.module.security.securityFragment.registerScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class RegisterPresenter : BaseMvpPresenterImpl<RegisterView.View>(),
    RegisterView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}