package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.projectList.ProjectInfoDetail
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface ProjectDetailService {

    @POST(ApiSettings.PATH_PROJECT_DETAIL)
    fun projectDetail(
        @HeaderMap header: Map<String, String>,
        @Body body: Int?
    ): Observable<ProjectInfoDetail.ProjectInfoDetailRes>
}