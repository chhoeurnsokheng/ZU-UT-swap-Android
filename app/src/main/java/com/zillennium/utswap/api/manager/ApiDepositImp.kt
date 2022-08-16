package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.deposite.DepositObj
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */
class ApiDepositImp : ApiManager() {
    fun getLIstPaymentMethod(context: Context): Observable<DepositObj> =
        mDespositeService.getLIstPaymentMethod(
            Header.getHeader(
                Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun depositMoney(
        context: Context,
        body: DepositObj.DepositRequestBody
    ): Observable<DepositObj> =
        mDespositeService.depositMoney(
            Header.getHeader(
                Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            ), body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getFinanceTransferLog(context: Context): Observable<DepositObj> =
        mDespositeService.getFinanceTransferLog(
            Header.getHeader(
                Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}