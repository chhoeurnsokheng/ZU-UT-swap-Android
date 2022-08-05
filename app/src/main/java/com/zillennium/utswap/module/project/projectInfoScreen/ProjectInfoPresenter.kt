package com.zillennium.utswap.module.project.projectInfoScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectDetailImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.projectList.ProjectInfoDetail
import rx.Subscription

class ProjectInfoPresenter : BaseMvpPresenterImpl<ProjectInfoView.View>(),
    ProjectInfoView.Presenter {
    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun projectDetail(body: Int?, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiProjectDetailImp().projectDetail(body, context).subscribe({
            if (it.status == 1){
                mView?.projectDetailSuccess(it.data as ProjectInfoDetail.ProjectInfoDetailData)

                println("========================" + it.data)
            }else{
                mView?.projectDetailFail(it)
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