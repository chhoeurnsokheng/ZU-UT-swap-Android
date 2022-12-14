package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.home.ForceUpdate
import com.zillennium.utswap.models.userService.User
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import rx.Observable

/**
 * Created by Sokheng Chhoeurn on 4/8/22.
 * Build in Mac
 */
interface HomeService {
    @GET(ApiSettings.PATH_GET_BANNNER)
    fun getBanner(
        @HeaderMap headers: Map<String, String>
    ): Observable<BannerObj.Banner>

    @GET(ApiSettings.PATH_GET_HOME_WISHLIST_BALANCE)
    fun getWhistListAndBalance(
        @HeaderMap headers: Map<String, String>
    ): Observable<BannerObj.whistListRes>

    @GET(ApiSettings.PATH_FORCE_UPDATE)
    fun checkForceUpdate(): Observable<ForceUpdate.ForceUpdateRes>

    @GET(ApiSettings.PATH_KYC_STATUS)
    fun checkKYCStatus(
        @HeaderMap headerMap: Map<String, String>
    ): Observable<User.KycRes>

}