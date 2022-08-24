package com.zillennium.utswap.module.project.subscriptionScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiHomeImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.project.SubscriptionProject
import rx.Subscription

class SubscriptionPresenter : BaseMvpPresenterImpl<SubscriptionView.View>(),
    SubscriptionView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onCheckKYCStatus() {
        mContext?.let {
            onCheckKYCStatusSubscription = ApiHomeImp().checkKycStatus(it).subscribe({ it1 ->
                if (it1.status == "1") {
                    mView?.onCheckKYCSuccess(it1)
                } else {
                    mView?.onCheckKYCFail()

                }
            }, { it2 ->
                object : CallbackWrapper(it2, it, arrayListOf()) {
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.onCheckKYCFail()
                    }
                }

            })

        }

    }


    var onCheckKYCStatusSubscription: Subscription? = null
    override fun onUnSubscript() {
        onCheckKYCStatusSubscription?.unsubscribe()
    }

    /**   Subscription Project   **/
    override fun onCheckSubscriptionStatus(
        body: SubscriptionProject.SubscriptionProjectBody,
        context: Context
    ) {
        subscription?.unsubscribe()
        subscription = ApiProjectImp().subscriptionProject(body, context).subscribe({
            if (it.status == 1) {
                mView?.onCheckSubscriptionSuccess(it)
            } else {
                mView?.onCheckSubscriptionFail(it)
            }

        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }

            }

        })
    }

}