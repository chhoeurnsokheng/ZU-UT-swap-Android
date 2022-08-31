package com.zillennium.utswap.module.finance.withdrawScreen.addBank

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiWithdrawImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import rx.Subscription


class AddBankPresenter : BaseMvpPresenterImpl<AddBankView.View>(),
    AddBankView.Presenter {
    var getListAvailableWithdrawBankSubscription: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getListAvailableWithdrawBank(context: Context) {
        getListAvailableWithdrawBankSubscription?.unsubscribe()
        getListAvailableWithdrawBankSubscription =
            ApiWithdrawImp().getListAvailableWithdrawBank(context).subscribe({
                mView?.onGetListAvailableWithdrawBankSuccess(it)
            }, { error ->
                object : CallbackWrapper(error, context, arrayListOf()) {
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.onGetListAvailableWithdrawBankFailed("Failed")
                    }

                }

            })
    }
}