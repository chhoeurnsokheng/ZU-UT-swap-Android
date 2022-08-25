package com.zillennium.utswap.module.finance.transferScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.*
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.financeTransfer.Transfer
import rx.Subscription

class TransferPresenter : BaseMvpPresenterImpl<TransferView.View>(),
        TransferView.Presenter {

    var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetUserInfo(context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().appSideBarUserInfo(context).subscribe({
            if(it.status == 1)
            {
                mView?.onGetUserInfoSuccess(it.data!!)
            }else{
                mView?.onGetUserInfoFail(it.data!!)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
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

    override fun onGetValidateTransfer(body: Transfer.GetValidateTransferObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiTransferImp().getFinanceValidateTransfer(body, context).subscribe({
            if (it.status == 1){
                mView?.onGetValidateTransferSuccess(it.data!!)
            }else{
                mView?.onGetValidateTransferFail(it)
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