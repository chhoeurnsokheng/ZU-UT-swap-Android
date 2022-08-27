package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.Observable

class ApiFinanceBalanceImp: ApiManager() {

    fun getUserBalanceInfo(context: Context): Observable<BalanceFinance.GetUserBalanceInfo> =
        mFinanceUserBalance.getUserBalance(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun getUserBalanceDateFilter(body: BalanceFinance.GetBalanceSearchDateObject, context: Context): Observable<BalanceFinance.GetBalanceSearchDate> =
        mFinanceUserBalance.getUserBalanceDateFilter(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun getUserBalanceExport(body: BalanceFinance.ExportFinanceBalanceObject, context: Context): Observable<BalanceFinance.ExportFinanceBalance> =
        mFinanceUserBalance.getExportUserBalance(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}