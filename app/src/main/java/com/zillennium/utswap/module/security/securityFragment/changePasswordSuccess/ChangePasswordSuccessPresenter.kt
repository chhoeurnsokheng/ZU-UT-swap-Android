package com.zillennium.utswap.module.security.securityFragment.changePasswordSuccess

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ChangePasswordSuccessPresenter : BaseMvpPresenterImpl<ChangePasswordSuccessView.View>(),
    ChangePasswordSuccessView.Presenter {

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}