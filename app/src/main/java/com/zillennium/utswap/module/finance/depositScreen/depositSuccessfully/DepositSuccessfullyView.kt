package com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.models.deposite.DepositObj

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */
class DepositSuccessfullyView {
    interface View : BaseMvpView {
        override fun initView()
//        fun onGetQueryOrderSuccess(data: DataQueryOrderObj.DataQueryOrderRes)
//        fun onGetQueryOrderFail(data: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
       // fun getQueryOrder(context: Context,  body: DepositObj.DataQueryOrderBody)
    }


}