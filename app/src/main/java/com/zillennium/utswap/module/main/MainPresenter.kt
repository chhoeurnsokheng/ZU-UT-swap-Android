package com.zillennium.utswap.module.main

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.JsonObject
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiHomeImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNotificationImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.notification.NotificationModel
import rx.Subscription

class MainPresenter : BaseMvpPresenterImpl<MainView.View>(),
    MainView.Presenter {
    var forceUpdateSubscription: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }


    override fun checkForceUpdate(context: Context) {
        forceUpdateSubscription?.unsubscribe()
        forceUpdateSubscription = ApiHomeImp().checkForceUpdate(context).subscribe({
            mView?.onGetForceUpdateSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, context.applicationContext, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onGetForceUpdateFailed("Failed")
                }

            }
        })}


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

    override fun getNotificationLists(context: Context) {
        val param = JsonObject()
        param.addProperty("page", 1)
        subscription = ApiNotificationImp().notification(context, param).subscribe({
            if (it.status == 1) {
                mView?.onNotificationSuccess(it.data ?: NotificationModel.NotificationData())
            } else {
                mView?.onNotificationFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(
                    status: ApiManager.NetworkErrorStatus,
                    data: Any
                ) {
                    mView?.onFail(data.toString())
                }

            }
        })
    }

    var onCheckKYCStatusSubscription: Subscription? = null
    private var subscription: Subscription? = null

    override fun onUnSubscript() {
        onCheckKYCStatusSubscription?.unsubscribe()
        subscription?.unsubscribe()

    }
}