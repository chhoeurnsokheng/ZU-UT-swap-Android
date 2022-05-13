package com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.fragment.nationalID

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NationalIDPresenter : BaseMvpPresenterImpl<NationalIDView.View>(),
    NationalIDView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}