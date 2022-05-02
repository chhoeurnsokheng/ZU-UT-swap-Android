package com.zillennium.utswap.screens.setting.accountTypeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.simple.SimpleView

class AccountTypePresenter : BaseMvpPresenterImpl<AccountTypeView.View>(),
    AccountTypeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}