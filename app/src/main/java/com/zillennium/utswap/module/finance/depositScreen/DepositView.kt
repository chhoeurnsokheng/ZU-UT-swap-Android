package com.zillennium.utswap.module.finance.depositScreen

import android.content.Context
import android.os.Bundle
import android.os.Message
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.deposite.DepositObj

class DepositView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetListBankSuccess(data:DepositObj.DepositRes)
        fun onGetListBankFailed(message:String)

        fun onDepositBalanceSuccess(data: DepositObj.DepositReturn)
        fun onDepositBalanceFailed(message: String)

        fun onGetDepositTransferBalanceLogSuccess(data: DepositObj.DepositRes)
        fun onGetDepositTransferBalanceLogFailed(message: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetListBank(context: Context)

        fun onDepositBalance(context: Context, body: DepositObj.DepositRequestBody)

        fun onGetDepositTransferBalanceLog(context: Context)
    }
}