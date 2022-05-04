package com.zillennium.utswap.screens.setting.fundPasswordScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class FundPasswordPresenter : BaseMvpPresenterImpl<FundPasswordView.View>(),
    FundPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}