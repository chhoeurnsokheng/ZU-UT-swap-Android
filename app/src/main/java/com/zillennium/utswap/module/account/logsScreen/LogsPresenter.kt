package com.zillennium.utswap.module.account.logsScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiAccountLogsImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.logs.Logs
import rx.Subscription

class LogsPresenter : BaseMvpPresenterImpl<LogsView.View>(),
    LogsView.Presenter {

    private var subscription: Subscription? = null
    private var subscriptionNextPage: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun accountLogs(body: Logs.AccountLogsObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiAccountLogsImp().accountLogs(body, context).subscribe({
            if (it.status == 1){
                mView?.accountLogsSuccess(it)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.accountLogsFail(it)
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

    override fun accountLogsNextPage(body: Logs.AccountLogsObject, context: Context) {
        subscriptionNextPage?.unsubscribe()
        subscriptionNextPage = ApiAccountLogsImp().accountLogs(body, context).subscribe({
            if (it.status == 1){
                mView?.accountLogsNextPageSuccess(it)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.accountLogsNextPageFail(it)
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