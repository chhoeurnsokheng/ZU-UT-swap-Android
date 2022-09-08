package com.zillennium.utswap.module.system.notification

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNotificationImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.BaseResponse.BaseResponse
import com.zillennium.utswap.models.notification.NotificationModel
import rx.Subscription

class NotificationPresenter : BaseMvpPresenterImpl<NotificationView.View>(),
    NotificationView.Presenter {

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getNotificationLists(context: Context, page: Int) {
        val param = JsonObject()
        param.addProperty("page", page)
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

    override fun readNotification(id: String) {
        mContext?.let { context ->
            val body = JsonObject()
            body.addProperty("id_notifi", id)
            readNotificationSubscription =
                ApiNotificationImp().onReadNotification(context, body).subscribe({
                    val dataRes = Gson().fromJson(it, BaseResponse::class.java)
                    if (dataRes.status == 1) {
                        mView?.onReadNotificationSuccess(dataRes.message.toString())
                    } else {
                        mView?.onReadNotificationFail()
                    }
                }, {

                    object : CallbackWrapper(it, context, arrayListOf()) {
                        override fun onCallbackWrapper(
                            status: ApiManager.NetworkErrorStatus,
                            data: Any
                        ) {
                            mView?.onFail(data)
                        }
                    }

                })

        }
    }

    override fun readAllNotification() {
        mContext?.let {context ->
            readAllNotificationSubscription = ApiNotificationImp().onReadAllNotification(context).subscribe({
               val dataRes = Gson().fromJson(it, BaseResponse::class.java)
                if (dataRes.status == 1) {
                    mView?.onReadAllNotificationSuccess(dataRes.message.toString())
                } else {
                    mView?.onReadAllNotificationFail()
                }
            },{
                object : CallbackWrapper(it, context, arrayListOf()){
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.onFail(data)
                    }
                }

            })

        }
    }

    private var subscription: Subscription? = null
    private var readNotificationSubscription: Subscription? = null
    private var readAllNotificationSubscription: Subscription? = null
    override fun onUnSubscript() {
        super.onUnSubscript()
        subscription?.unsubscribe()
        readAllNotificationSubscription?.unsubscribe()
        readNotificationSubscription?.unsubscribe()
    }
}