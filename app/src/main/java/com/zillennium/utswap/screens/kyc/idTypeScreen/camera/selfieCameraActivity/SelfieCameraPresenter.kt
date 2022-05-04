package com.zillennium.utswap.screens.kyc.idTypeScreen.camera.selfieCameraActivity

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SelfieCameraPresenter : BaseMvpPresenterImpl<SelfieCameraView.View>(),
    SelfieCameraView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}