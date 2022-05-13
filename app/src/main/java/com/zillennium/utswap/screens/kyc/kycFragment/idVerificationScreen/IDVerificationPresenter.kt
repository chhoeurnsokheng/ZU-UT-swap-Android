package com.zillennium.utswap.screens.kyc.kycFragment.idVerificationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class IDVerificationPresenter : BaseMvpPresenterImpl<IDVerificationView.View>(),
    IDVerificationView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}