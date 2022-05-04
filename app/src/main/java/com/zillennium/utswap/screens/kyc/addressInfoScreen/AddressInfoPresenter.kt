package com.zillennium.utswap.screens.kyc.addressInfoScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AddressInfoPresenter : BaseMvpPresenterImpl<AddressInfoView.View>(),
    AddressInfoView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}