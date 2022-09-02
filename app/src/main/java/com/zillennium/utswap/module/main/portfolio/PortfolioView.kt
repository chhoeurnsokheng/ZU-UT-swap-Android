package com.zillennium.utswap.module.main.portfolio

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.portfolio.Portfolio

class PortfolioView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetPortfolioSuccess(data: Portfolio.GetPortfolioData)
        fun onGetPortfolioFail(data: Portfolio.GetPortfolio)

        fun getPortfolioDashboardChartSuccess(dataSuccess: Portfolio.GetPortfolioDashboardChartRes)
        fun getPortfolioDashboardChartFailed(data: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetPortfolio(body: Portfolio.GetPortfolioObject, context: Context)
        fun getPortfolioDashboardChart(context: Context)
    }
}