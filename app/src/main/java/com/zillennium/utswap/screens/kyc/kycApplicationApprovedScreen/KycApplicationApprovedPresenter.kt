package com.zillennium.utswap.screens.kyc.kycApplicationApprovedScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.simple.SimpleView

class KycApplicationApprovedPresenter : BaseMvpPresenterImpl<KycApplicationApprovedView.View>(),
    KycApplicationApprovedView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}