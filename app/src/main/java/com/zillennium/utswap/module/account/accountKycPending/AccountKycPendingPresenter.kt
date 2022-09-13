package com.zillennium.utswap.module.account.accountKycPending

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AccountKycPendingPresenter : BaseMvpPresenterImpl<AccountKycPendingView.View>(),
    AccountKycPendingView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}