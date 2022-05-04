package com.zillennium.utswap.screens.wallet.historicalScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class HistoricalPresenter : BaseMvpPresenterImpl<HistoricalView.View>(),
    HistoricalView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}