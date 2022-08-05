package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.projectList.ProjectInfoDetail
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiProjectDetailImp : ApiManager() {
    fun projectDetail(
        body: Int?,
        context: Context
    ): Observable<ProjectInfoDetail.ProjectInfoDetailRes> =
        mProjectDetailService.projectDetail(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}