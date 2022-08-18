package com.zillennium.utswap.module.finance.balanceScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.financeBalance.BalanceFinance

class FinanceBalanceView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetUserBalanceInfoSuccess(data: BalanceFinance.GetUserBalanceInfoData)
        fun onGetUserBalanceInfoFail(data: BalanceFinance.GetUserBalanceInfo)
        fun onGetUserBalanceFilterDateSuccess(data: BalanceFinance.GetBalanceSearchDateData)
        fun onGetUserBalanceFilterDateFail(data: BalanceFinance.GetBalanceSearchDate)
        fun onGetExportBalanceSuccess(data: BalanceFinance.ExportFinanceBalanceData)
        fun onGetExportBalanceFail(data: BalanceFinance.ExportFinanceBalance)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetUserBalanceInfo(context: Context)
        fun onGetUserBalanceFilterDate(body: BalanceFinance.GetBalanceSearchDateObject, context: Context)
        fun onGetExportBalance(body: BalanceFinance.ExportFinanceBalanceObject, context: Context)
    }
}