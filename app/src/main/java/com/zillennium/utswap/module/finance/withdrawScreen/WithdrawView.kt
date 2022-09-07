package com.zillennium.utswap.module.finance.withdrawScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.financeBalance.BalanceFinance

class WithdrawView {
    interface View : BaseMvpView {
        override fun initView()
        fun getUserBalanceStatusSuccess(data: BalanceFinance.GetUserBalanceInfo)
        fun getUserBalanceStatusFail(data: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getUSerBalanceStatus(context: Context)
    }
}