package com.zillennium.utswap.module.main.portfolio

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiPortfolioImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.portfolio.Portfolio
import rx.Subscription

class PortfolioPresenter : BaseMvpPresenterImpl<PortfolioView.View>(),
    PortfolioView.Presenter {

    var subscription: Subscription? = null
    var getPortfolioDashboardChartSubscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetPortfolio(body: Portfolio.GetPortfolioObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiPortfolioImp().getPortfolio(body, context).subscribe({
            if (it.status == 1) {
                mView?.onGetPortfolioSuccess(it)
            } else {
                mView?.onGetPortfolioFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun getPortfolioDashboardChart(context: Context) {
        getPortfolioDashboardChartSubscription?.unsubscribe()
        getPortfolioDashboardChartSubscription =
            ApiPortfolioImp().getPortfolioDashboardChart(context).subscribe({
                mView?.getPortfolioDashboardChartSuccess(it)
            }, { error ->
                object : CallbackWrapper(error, context, arrayListOf()) {
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.getPortfolioDashboardChartFailed("Failed")
                    }

                }
            })
    }
}