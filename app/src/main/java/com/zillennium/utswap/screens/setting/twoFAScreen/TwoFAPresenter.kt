package com.zillennium.utswap.screens.setting.twoFAScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TwoFAPresenter : BaseMvpPresenterImpl<TwoFAView.View>(),
    TwoFAView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}