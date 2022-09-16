package com.zillennium.utswap.module.project.projectScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.JsonObject
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNotificationImp
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.models.project.ProjectList
import rx.Subscription


class ProjectPresenter : BaseMvpPresenterImpl<ProjectView.View>(),
    ProjectView.Presenter {

    private var subscription: Subscription? = null
    private var notificationSubscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun projectList(body: ProjectList.ProjectListBody) {
        subscription?.unsubscribe()
        subscription = ApiProjectImp().projectList(body).subscribe({
            mView?.projectListSuccess(it)

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

    override fun getNotificationLists(context: Context) {
        val param = JsonObject()
        param.addProperty("page", 1)
        notificationSubscription = ApiNotificationImp().notification(context, param).subscribe({
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
}
