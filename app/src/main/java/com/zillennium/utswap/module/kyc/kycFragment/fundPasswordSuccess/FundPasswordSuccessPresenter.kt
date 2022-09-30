package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordSuccess

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class FundPasswordSuccessPresenter : BaseMvpPresenterImpl<FundPasswordSuccessView.View>(),
    FundPasswordSuccessView.Presenter {

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

}