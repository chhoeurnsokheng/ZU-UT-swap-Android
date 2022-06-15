package com.zillennium.utswap.screens.finance.withdrawActivity

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.simple.SimpleView

class WithdrawPresenter : BaseMvpPresenterImpl<WithdrawView.View>(),
        WithdrawView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}