package com.zillennium.utswap.screens.security.securityFragment.termConditionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TermConditionPresenter : BaseMvpPresenterImpl<TermConditionView.View>(),
    TermConditionView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}