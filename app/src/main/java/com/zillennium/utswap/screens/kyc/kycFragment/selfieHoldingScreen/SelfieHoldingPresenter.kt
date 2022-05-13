package com.zillennium.utswap.screens.kyc.kycFragment.selfieHoldingScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SelfieHoldingPresenter : BaseMvpPresenterImpl<SelfieHoldingView.View>(),
    SelfieHoldingView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}