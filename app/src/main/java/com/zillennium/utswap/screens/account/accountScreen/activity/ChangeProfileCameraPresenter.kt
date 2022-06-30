package com.zillennium.utswap.screens.account.accountScreen.activity

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ChangeProfileCameraPresenter : BaseMvpPresenterImpl<ChangeProfileCameraView.View>(),
    ChangeProfileCameraView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}