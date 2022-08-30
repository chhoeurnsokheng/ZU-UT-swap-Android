package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.models.deposite.DepositObj

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */

class DepositopenLinkView {
    interface View : BaseMvpView {
        override fun initView()
        fun getQueryOrderSuccess(data : DataQueryOrderObj.DataQueryOrderRes)
        fun getQueryOrderFail(data : String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getQueryOrder(context: Context, body: DepositObj.DataQueryOrderBody)
    }
}