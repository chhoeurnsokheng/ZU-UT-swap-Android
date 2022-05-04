package com.zillennium.utswap.screens.wallet.transferScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TransferPresenter : BaseMvpPresenterImpl<TransferView.View>(),
    TransferView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}