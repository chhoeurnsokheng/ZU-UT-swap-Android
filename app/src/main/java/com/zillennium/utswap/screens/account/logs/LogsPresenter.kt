package com.zillennium.utswap.screens.account.logs

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class LogsPresenter : BaseMvpPresenterImpl<LogsView.View>(),
    LogsView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}