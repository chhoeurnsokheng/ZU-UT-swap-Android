package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */

class DepositopenLinkPresenter : BaseMvpPresenterImpl<DepositopenLinkView.View>(),
DepositopenLinkView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}