package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.project.ProjectInfoDetail
import com.zillennium.utswap.models.project.ProjectList
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface ProjectService {

    /**     Project List             **/
    @POST(ApiSettings.PATH_PROJECT_LIST)
    fun projectList(
        @Body body: ProjectList.ProjectListObject
    ): Observable<ProjectList.ProjectListRes>


    /**    Project Detail     **/
    @POST(ApiSettings.PATH_PROJECT_DETAIL)
    fun projectDetail(
        @HeaderMap header: Map<String, String>,
        @Body body: ProjectInfoDetail.ProjectInfoDetailObject

    ): Observable<ProjectInfoDetail.ProjectInfoDetailRes>


}