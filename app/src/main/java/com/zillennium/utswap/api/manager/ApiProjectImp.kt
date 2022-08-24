package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.project.ProjectInfoDetail
import com.zillennium.utswap.models.project.ProjectList
import com.zillennium.utswap.models.project.SubscriptionProject
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiProjectImp : ApiManager() {


    /**     Project List        **/
    fun projectList(
        body: ProjectList.ProjectListBody
    ): Observable<ProjectList.ProjectListRes> =
        mProjectService.projectList(body)
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

    /**     Subscription Project      **/
    fun subscriptionProject(
        body: SubscriptionProject.SubscriptionProjectBody,
        context: Context
    ): Observable<SubscriptionProject.SubscriptionProjectRes> =
        mProjectService.subscriptionProject(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}