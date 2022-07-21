package com.zillennium.utswap.module.account.accountDetailScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AccountDetailPresenter : BaseMvpPresenterImpl<AccountDetailView.View>(),
    AccountDetailView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}