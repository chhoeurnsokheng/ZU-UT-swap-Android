package com.zillennium.utswap.module.finance.withdrawScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class WithdrawPresenter : BaseMvpPresenterImpl<WithdrawView.View>(),
        WithdrawView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}