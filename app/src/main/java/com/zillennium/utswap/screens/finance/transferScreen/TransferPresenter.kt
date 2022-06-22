package com.zillennium.utswap.screens.finance.transferScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.simple.SimpleView

class TransferPresenter : BaseMvpPresenterImpl<TransferView.View>(),
        TransferView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}