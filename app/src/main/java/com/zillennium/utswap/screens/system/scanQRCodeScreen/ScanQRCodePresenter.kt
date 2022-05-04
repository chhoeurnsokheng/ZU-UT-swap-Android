package com.zillennium.utswap.screens.system.scanQRCodeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ScanQRCodePresenter : BaseMvpPresenterImpl<ScanQRCodeView.View>(),
    ScanQRCodeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}