package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.user.LoginVerifyLoginParam
import com.zillennium.utswap.models.user.VerifiyCode
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

/**
 * @author chhoeurnsokheng
 * Created 7/7/22 at 2:49 PM
 * By Mac
 */

interface UserService {
    @POST(ApiSettings.PATH_USER_LOGIN)
    fun loginVerifyCode(
        @HeaderMap headers: Map<String, String>,
        @Body body: LoginVerifyLoginParam
    ): Observable<VerifiyCode>

}