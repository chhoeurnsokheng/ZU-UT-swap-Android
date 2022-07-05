package com.zillennium.utswap.module.finance.withdrawScreen.addBank

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl


class AddBankPresenter : BaseMvpPresenterImpl<AddBankView.View>(),
        AddBankView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}