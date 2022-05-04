package com.zillennium.utswap.screens.wallet.withdrawalScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class WithdrawalPresenter : BaseMvpPresenterImpl<WithdrawalView.View>(),
    WithdrawalView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}