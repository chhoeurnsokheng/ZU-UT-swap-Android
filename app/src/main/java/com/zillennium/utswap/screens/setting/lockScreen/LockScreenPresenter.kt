package com.zillennium.utswap.screens.setting.lockScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class LockScreenPresenter : BaseMvpPresenterImpl<LockScreenView.View>(),
    LockScreenView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}