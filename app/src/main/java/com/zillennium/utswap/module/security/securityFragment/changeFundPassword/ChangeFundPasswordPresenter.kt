package com.zillennium.utswap.module.security.securityFragment.changeFundPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ChangeFundPasswordPresenter : BaseMvpPresenterImpl<ChangeFundPasswordView.View>(),
    ChangeFundPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}