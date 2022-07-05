package com.zillennium.utswap.module.security.securityFragment.PromptScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class PromptPresenter : BaseMvpPresenterImpl<PromptView.View>(),
    PromptView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}