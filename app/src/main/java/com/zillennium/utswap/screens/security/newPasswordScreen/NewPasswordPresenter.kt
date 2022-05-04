package com.zillennium.utswap.screens.security.newPasswordScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NewPasswordPresenter : BaseMvpPresenterImpl<NewPasswordView.View>(),
    NewPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}