package com.zillennium.utswap.module.security.securityFragment.changeFundPasswordSuccess

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ChangeFundPasswordSuccessPresenter : BaseMvpPresenterImpl<ChangeFundPasswordSuccessView.View>(),
    ChangeFundPasswordSuccessView.Presenter {

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}