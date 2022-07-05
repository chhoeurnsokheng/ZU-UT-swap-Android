package com.zillennium.utswap.module.kyc.kycActivity

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInView

class KYCPresenter : BaseMvpPresenterImpl<SignInView.View>(),
    SignInView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}