package com.zillennium.utswap.screens.navbar.swapTab

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SwapPresenter : BaseMvpPresenterImpl<SwapView.View>(),
    SwapView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}