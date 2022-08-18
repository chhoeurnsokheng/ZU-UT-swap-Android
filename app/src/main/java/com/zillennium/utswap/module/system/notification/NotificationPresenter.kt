package com.zillennium.utswap.module.system.notification

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNotificationImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.notification.NotificationModel
import rx.Subscription

class NotificationPresenter : BaseMvpPresenterImpl<NotificationView.View>(),
    NotificationView.Presenter {
    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getCustomerSupport(context: Context) {
        subscription?.unsubscribe()
        subscription = ApiNotificationImp().notification(context).subscribe({
            mView?.onNotificationSuccess(it.data!!)
            mView?.onNotificationFail(it)
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
}