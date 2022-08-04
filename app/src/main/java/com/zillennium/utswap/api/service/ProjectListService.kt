package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.projectList.ProjectList
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface ProjectListService {
    @POST(ApiSettings.PATH_PROJECT_LIST)
    fun projectList(
        @Query("name") name : String,
        @Query("page") page : Int
    ): Observable<ProjectList.ProjectListRes>
}