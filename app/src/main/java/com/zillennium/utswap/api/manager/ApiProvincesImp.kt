package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.province.Province
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by Sokheng Chhoeurn on 2/8/22.
 * Build in Mac
 */
class ApiProvincesImp : ApiManager() {
    fun getAllProvinces(context: Context): Observable<Province> =
        mProvince.getAllProvince(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context)
        )
            .subscribeOn(rx.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun queryProvince(context: Context, parent_code: String): Observable<Province> =
        mProvince.queryProvince(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            parent_code
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}