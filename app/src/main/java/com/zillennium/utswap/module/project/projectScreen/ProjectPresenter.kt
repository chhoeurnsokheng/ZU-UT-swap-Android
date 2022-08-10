package com.zillennium.utswap.module.project.projectScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.project.ProjectList
import rx.Subscription


class ProjectPresenter : BaseMvpPresenterImpl<ProjectView.View>(),
    ProjectView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun projectList(name: String, page: Int, search: String, sortedDate: Boolean) {
        subscription?.unsubscribe()
        subscription = ApiProjectImp().projectList(
            name,
            page
        ).subscribe({
            if (it.status == 1) {
                mView?.projectListSuccess(it.data as ArrayList<ProjectList.ProjectListData>)

            } else {
                mView?.projectListFail(it)
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
