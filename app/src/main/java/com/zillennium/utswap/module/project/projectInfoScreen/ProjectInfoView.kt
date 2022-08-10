package com.zillennium.utswap.module.project.projectInfoScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.project.ProjectInfoDetail

class ProjectInfoView {
    interface View : BaseMvpView {
        override fun initView()
        fun projectInfoViewSuccess(data: ProjectInfoDetail.ProjectInfoDetailData)
        fun projectInfoViewFail(data: ProjectInfoDetail.ProjectInfoDetailRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun projectInfoView(body: ProjectInfoDetail.ProjectInfoDetailObject, context: Context)
    }
}