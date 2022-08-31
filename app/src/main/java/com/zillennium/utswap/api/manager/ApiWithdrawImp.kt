package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.withdraw.WithdrawObj
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Sokheng Chhoeurn on 31/8/22.
 * Build in Mac
 */
class ApiWithdrawImp : ApiManager() {

    fun getListAvailableWithdrawBank(context: Context): Observable<WithdrawObj.WithdrawRes> =
        mWithDrawService.getListAvailableWithdrawBank(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


}