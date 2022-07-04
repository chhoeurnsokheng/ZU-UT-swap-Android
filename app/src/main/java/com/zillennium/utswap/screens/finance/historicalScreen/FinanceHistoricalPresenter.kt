package com.zillennium.utswap.screens.finance.historicalScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class FinanceHistoricalPresenter : BaseMvpPresenterImpl<FinanceHistoricalView.View>(),
        FinanceHistoricalView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}