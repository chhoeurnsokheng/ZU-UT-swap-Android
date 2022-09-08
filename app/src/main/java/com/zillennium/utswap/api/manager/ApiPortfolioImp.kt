package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.portfolio.Portfolio
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiPortfolioImp: ApiManager() {

    fun getPortfolio(body: Portfolio.GetPortfolioObject, context: Context): Observable<Portfolio.GetPortfolio> =
        mPortfolio.getPortfolio(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getPortfolioDashboardChart(context: Context):Observable<Portfolio.GetPortfolioDashboardChartRes> =
        mPortfolio.getPortfolioDashboardChart(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}