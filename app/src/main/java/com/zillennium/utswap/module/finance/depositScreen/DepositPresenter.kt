package com.zillennium.utswap.module.finance.depositScreen

import android.content.Context
import android.os.Bundle
import androidx.appcompat.view.SupportActionModeWrapper
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiDepositImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.deposite.DepositObj
import rx.Subscription

class DepositPresenter : BaseMvpPresenterImpl<DepositView.View>(),
    DepositView.Presenter {


    var subscriptionOnGetListBank: Subscription? = null
    var subscriptionOnDepositBalance: Subscription? = null
    var subscriptionOnGetDepositTransferBalanceLog: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetListBank(context: Context) {
        subscriptionOnGetListBank?.unsubscribe()
        subscriptionOnGetListBank = ApiDepositImp().getLIstPaymentMethod(context).subscribe({
            mView?.onGetListBankSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onDepositBalanceFailed("Failed")
                }
            }

        })
    }

    override fun onDepositBalance(context: Context, body: DepositObj.DepositRequestBody) {
        subscriptionOnDepositBalance?.unsubscribe()
        subscriptionOnDepositBalance = ApiDepositImp().depositMoney(context, body).subscribe({
            mView?.onDepositBalanceSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onDepositBalanceFailed("Failed")
                }
            }

        })
    }

    override fun onGetDepositTransferBalanceLog(context: Context) {
        subscriptionOnGetDepositTransferBalanceLog?.unsubscribe()
        subscriptionOnGetDepositTransferBalanceLog =
            ApiDepositImp().getFinanceTransferLog(context).subscribe({
                mView?.onGetDepositTransferBalanceLogSuccess(it)
            }, { error ->
                object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.onGetDepositTransferBalanceLogFailed("Failed")
                    }
                }

            })
    }
}