package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription


class FundPasswordPresenter : BaseMvpPresenterImpl<FundPasswordView.View>(),
    FundPasswordView.Presenter {
    var addKYCSubscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun addKyc(data: User.Kyc, context: Context) {
        addKYCSubscription?.unsubscribe()
        addKYCSubscription = ApiUserImp().addKyc(data, context).subscribe({
            mView?.addKycSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, context, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.addKycFail(data.toString())
                }
            }
        })
    }
}