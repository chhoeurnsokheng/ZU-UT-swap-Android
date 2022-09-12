package com.zillennium.utswap.module.finance.historicalScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiHistoricalImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.financeHistorical.Historical
import rx.Subscription
import rx.subscriptions.Subscriptions

class FinanceHistoricalPresenter : BaseMvpPresenterImpl<FinanceHistoricalView.View>(),
        FinanceHistoricalView.Presenter {

    var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetMarketName() {
        subscription?.unsubscribe()
        subscription = ApiHistoricalImp().getMarketName().subscribe({
            if(it.status == 1){
                mView?.onGetMarketNameSuccess(it.data)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onGetMarketNameFail(it)
                }
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }

        })
    }
    override fun onGetUserTransaction(body: Historical.UserTransactionObject, context:Context) {
        subscription?.unsubscribe()
        subscription = ApiHistoricalImp().userTransaction(body, context).subscribe({
            if(it.status == 1){
                mView?.onGetUserTransactionSuccess(it.data!!)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onGetUserTransactionFail(it)
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
    override fun onGetTradeTransaction(body: Historical.TradeTransactionObject, context: Context) {
        Subscriptions.unsubscribed()
        subscription = ApiHistoricalImp().tradeTransaction(body, context).subscribe({
            if(it.status == 1){
                mView?.onGetTradeTransactionSuccess(it.data!!)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onGetTradeTransactionFail(it)
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
    override fun onGetAllTransaction(body: Historical.AllTransactionObject, context: Context) {
        Subscriptions.unsubscribed()
        subscription = ApiHistoricalImp().allTransaction(body, context).subscribe({
            if(it.status == 1){
                mView?.onGetAllTransactionSuccess(it.data!!)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onGetAllTransactionFail(it)
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
    override fun onExportHistorical(body: Historical.exportHistoricalObject, context: Context) {
        Subscriptions.unsubscribed()
        subscription = ApiHistoricalImp().exportHistorical(body, context).subscribe({
            if(it.status == 1){
                mView?.onExportHistoricalSuccess(it.data!!)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onExportHistoricalFail(it)
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
}