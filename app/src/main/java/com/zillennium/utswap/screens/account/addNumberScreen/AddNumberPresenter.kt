package com.zillennium.utswap.screens.account.addNumberScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AddNumberPresenter : BaseMvpPresenterImpl<AddNumberView.View>(),
    AddNumberView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}