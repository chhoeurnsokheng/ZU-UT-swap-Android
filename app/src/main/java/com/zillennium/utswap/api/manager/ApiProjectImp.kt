package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.project.ProjectInfoDetail
import com.zillennium.utswap.models.project.ProjectList
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiProjectImp : ApiManager() {


    /**     Project List        **/
    fun projectList(
        name: String,
        page: Int
    ): Observable<ProjectList.ProjectListRes> =
        mProjectService.projectList(
            name,
            page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    /**     Project Detail      **/
    fun projectDetail(
        body: ProjectInfoDetail.ProjectInfoDetailObject,
        context: Context
    ): Observable<ProjectInfoDetail.ProjectInfoDetailRes> =
        mProjectService.projectDetail(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


}