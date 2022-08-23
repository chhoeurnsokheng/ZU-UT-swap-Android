package com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */
class DepositSuccessfullyPresenter:BaseMvpPresenterImpl<DepositSuccessfullyView.View>(),
    DepositSuccessfullyView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        super.initViewPresenter(context, bundle)
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}