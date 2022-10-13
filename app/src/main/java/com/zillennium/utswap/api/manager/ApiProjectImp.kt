package com.zillennium.utswap.api.manager

import android.content.Context
import com.google.gson.JsonObject
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


    fun subscriptionProjectTermConditionSubmit(
        body: JsonObject,
        context: Context
    ): Observable<SubscriptionProject.SubScribeTermCondition> =
        mProjectService.subscriptionProjectTermCondition(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun checkProjectStatus(
       body: ProjectInfoDetail.ProjectTerCondition,
        context: Context
    ): Observable<SubscriptionProject.SubScribeTermCondition> =
        mProjectService.checkStatusProject(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /**     Subscription Project Order     **/
    fun subscriptionProjectOrder(
        body: SubscriptionProject.SubscribeOrderBody,
        context: Context
    ): Observable<SubscriptionProject.SubscriptionOrderRes> =
        mProjectService.subscriptionProjectOrder(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Check Subscription Project Balance*/
    fun subscriptionProjectCheck(
        body: SubscriptionProject.SubscriptionCheckObj,
        context: Context
    ): Observable<SubscriptionProject.SubscriptionCheckRes> =
        mProjectService.subscriptionProjectCheck(
            Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}