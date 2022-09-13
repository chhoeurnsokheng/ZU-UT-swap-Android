package com.zillennium.utswap.module.finance.withdrawScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiFinanceBalanceImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import rx.Subscription

class WithdrawPresenter : BaseMvpPresenterImpl<WithdrawView.View>(),
        WithdrawView.Presenter {
    var getBalanceUserSubscription:Subscription?  =null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getUSerBalanceStatus(context: Context) {
        getBalanceUserSubscription?.unsubscribe()
        getBalanceUserSubscription = ApiFinanceBalanceImp().getUserBalanceInfo(context).subscribe({
            mView?.getUserBalanceStatusSuccess(it)
        },{error-> object :CallbackWrapper(error, context, arrayListOf()){
            override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                mView?.getUserBalanceStatusFail("Failed")
            }
        }
        })
    }
}