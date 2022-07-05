package com.zillennium.utswap.module.main.portfolio

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class PortfolioPresenter : BaseMvpPresenterImpl<PortfolioView.View>(),
    PortfolioView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}