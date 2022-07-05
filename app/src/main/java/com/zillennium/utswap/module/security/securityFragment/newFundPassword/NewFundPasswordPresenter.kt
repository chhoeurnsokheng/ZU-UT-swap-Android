package com.zillennium.utswap.module.security.securityFragment.newFundPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NewFundPasswordPresenter : BaseMvpPresenterImpl<NewFundPasswordView.View>(),
    NewFundPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}