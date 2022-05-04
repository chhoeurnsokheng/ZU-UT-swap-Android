package com.zillennium.utswap.screens.kyc.idTypeScreen.fragment.nationalID

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.simple.SimpleView

class NationalIDPresenter : BaseMvpPresenterImpl<NationalIDView.View>(),
    NationalIDView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}