package com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class IDCardCameraPresenter : BaseMvpPresenterImpl<IDCardCameraView.View>(),
    IDCardCameraView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}