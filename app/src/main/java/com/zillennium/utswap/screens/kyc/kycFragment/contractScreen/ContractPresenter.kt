package com.zillennium.utswap.screens.kyc.kycFragment.contractScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ContractPresenter : BaseMvpPresenterImpl<ContractView.View>(),
    ContractView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}