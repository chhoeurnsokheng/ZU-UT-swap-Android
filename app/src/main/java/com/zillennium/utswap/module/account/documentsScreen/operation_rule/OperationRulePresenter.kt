package com.zillennium.utswap.module.account.documentsScreen.operation_rule

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.module.account.documentsScreen.contract.ContractView

/**
 * Created by Sokheng Chhoeurn on 18/7/22.
 * Build in Mac
 */
class OperationRulePresenter : BaseMvpPresenterImpl<OperationRuleView.View>(),
    OperationRuleView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

}