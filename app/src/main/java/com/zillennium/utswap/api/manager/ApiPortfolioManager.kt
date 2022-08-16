package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.portfolio.PortfolioFilterObj
import com.zillennium.utswap.models.portfolio.PortfolioGraphObj
import rx.schedulers.Schedulers
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Sokheng Chhoeurn on 15/8/22.
 * Build in Mac
 */

class ApiPortfolioManager : ApiManager() {
    fun getPortfolioGraph(context: Context): Observable<PortfolioGraphObj> =
        mPortfolioService.getPortfolioGraph(
            com.zillennium.utswap.api.Header.getHeader(
                com.zillennium.utswap.api.Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getPortfolioFilter(context: Context, type: Int): Observable<PortfolioFilterObj> =
        mPortfolioService.getPortfolioFilter(
            Header.getHeader(
                Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            ), type
        )
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}