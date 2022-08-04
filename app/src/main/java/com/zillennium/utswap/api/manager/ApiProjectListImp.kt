package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.models.projectList.ProjectList
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiProjectListImp : ApiManager() {
    fun projectList(
        name: String,
        page: Int
    ): Observable<ProjectList.ProjectListRes> =
        mProjectList.projectList(
            name,
            page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}