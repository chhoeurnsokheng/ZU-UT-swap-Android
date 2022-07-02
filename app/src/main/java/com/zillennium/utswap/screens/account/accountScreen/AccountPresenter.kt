package com.zillennium.utswap.screens.account.accountScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AccountPresenter : BaseMvpPresenterImpl<AccountView.View>(),
    AccountView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}