package com.zillennium.utswap.screens.kyc.kycFragment.kycApplicationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class KycApplicationPresenter : BaseMvpPresenterImpl<KycApplicationView.View>(),
    KycApplicationView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}