package com.zillennium.utswap.screens.account.verificationAccunt

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class VerificationAccountPresenter : BaseMvpPresenterImpl<VerificationAccountView.View>(),
    VerificationAccountView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}