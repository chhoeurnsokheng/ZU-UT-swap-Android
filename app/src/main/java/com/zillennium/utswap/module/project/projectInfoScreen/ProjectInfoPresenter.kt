package com.zillennium.utswap.module.project.projectInfoScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.project.ProjectInfoDetail
import rx.Subscription

class ProjectInfoPresenter : BaseMvpPresenterImpl<ProjectInfoView.View>(),
    ProjectInfoView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun projectInfoView(
        body: ProjectInfoDetail.ProjectInfoDetailObject,
        context: Context
    ) {
        subscription?.unsubscribe()

        subscription = ApiProjectImp().projectDetail(body, context).subscribe({
            println("print presenter ${it}")

            if (it.status == 1){
                mView?.projectInfoViewSuccess(it.data as ProjectInfoDetail.ProjectInfoDetailData)

            }
            else{
                mView?.projectInfoViewFail(it)
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