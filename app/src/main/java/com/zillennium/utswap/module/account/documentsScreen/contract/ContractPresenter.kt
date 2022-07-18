package com.zillennium.utswap.module.account.documentsScreen.contract

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

/**
 * Created by Sokheng Chhoeurn on 18/7/22.
 * Build in Mac
 */

class ContractPresenter: BaseMvpPresenterImpl<ContractView.View>(),
    ContractView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }


}