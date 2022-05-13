package com.zillennium.utswap.screens.security.securityFragment.verificationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class VerificationPresenter : BaseMvpPresenterImpl<VerificationView.View>(),
    VerificationView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}