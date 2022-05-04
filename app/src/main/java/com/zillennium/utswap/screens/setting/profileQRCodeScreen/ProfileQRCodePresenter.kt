package com.zillennium.utswap.screens.setting.profileQRCodeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ProfileQRCodePresenter : BaseMvpPresenterImpl<ProfileQRCodeView.View>(),
    ProfileQRCodeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}