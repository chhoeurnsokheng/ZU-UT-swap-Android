package com.zillennium.utswap.module.project.projectScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.project.ProjectList

class ProjectView {
    interface View : BaseMvpView {
        override fun initView()
        fun projectListSuccess(data: ArrayList<ProjectList.ProjectListData>)
        fun projectListFail(data: ProjectList.ProjectListRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun projectList(name: String, page: Int, search: String, sortedDate: Boolean)
    }
}