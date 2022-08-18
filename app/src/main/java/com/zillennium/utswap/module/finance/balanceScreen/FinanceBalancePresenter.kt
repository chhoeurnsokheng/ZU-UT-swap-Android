package com.zillennium.utswap.module.finance.balanceScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiFinanceBalanceImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import rx.Subscription

class FinanceBalancePresenter : BaseMvpPresenterImpl<FinanceBalanceView.View>(),
    FinanceBalanceView.Presenter {

    var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetUserBalanceInfo(context: Context) {
        subscription?.unsubscribe()
        subscription = ApiFinanceBalanceImp().getUserBalanceInfo(context).subscribe({
            if(it.status == 1){
                it.data?.let { it1 -> mView?.onGetUserBalanceInfoSuccess(it1) }
            }else{
                mView?.onGetUserBalanceInfoFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onGetUserBalanceFilterDate(body: BalanceFinance.GetBalanceSearchDateObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiFinanceBalanceImp().getUserBalanceDateFilter(body, context).subscribe({
            if (it.status == 1){
                mView?.onGetUserBalanceFilterDateSuccess(it.data!!)
            }else{
                mView?.onGetUserBalanceFilterDateFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun onGetExportBalance(body: BalanceFinance.ExportFinanceBalanceObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiFinanceBalanceImp().getUserBalanceExport(body, context).subscribe({
            if (it.status == 1){
                it.data?.let { it1 -> mView?.onGetExportBalanceSuccess(it1) }
            }else{
                mView?.onGetExportBalanceFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
}