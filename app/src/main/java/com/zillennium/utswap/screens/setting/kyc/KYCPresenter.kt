package com.zillennium.utswap.screens.setting.kyc

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class KYCPresenter : BaseMvpPresenterImpl<KYCView.View>(),
    KYCView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}