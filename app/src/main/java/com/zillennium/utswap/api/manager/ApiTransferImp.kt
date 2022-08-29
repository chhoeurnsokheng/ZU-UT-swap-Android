package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.financeTransfer.Transfer
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiTransferImp: ApiManager() {

    fun getFinanceTransfer(body: Transfer.GetTransferObject, context: Context): Observable<Transfer.GetTransfer> =
        mTransfer.getFinanceTransfer(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getFinanceValidateTransfer(body: Transfer.GetValidateTransferObject, context: Context) : Observable<Transfer.GetValidateTransfer> =
        mTransfer.getFinanceValidateTransfer(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}