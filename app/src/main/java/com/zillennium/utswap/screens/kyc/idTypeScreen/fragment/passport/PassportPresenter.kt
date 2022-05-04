package com.zillennium.utswap.screens.kyc.idTypeScreen.fragment.passport

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class PassportPresenter : BaseMvpPresenterImpl<PassportView.View>(),
    PassportView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}