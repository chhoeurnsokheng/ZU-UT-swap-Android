package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.province.Province
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Sokheng Chhoeurn on 2/8/22.
 * Build in Mac
 */
interface ProvincesService {
    @POST(ApiSettings.PATH_GET_PROVINCES)
    fun getAllProvince(
        @HeaderMap headers: Map<String,String>
    ):Observable<Province>

    @POST(ApiSettings.PATH_GET_PROVINCES)
    fun queryProvince(
        @HeaderMap headers: Map<String,String>,
        @Body parent_code :String
    ):Observable<Province>
}