package com.zillennium.utswap.api.service


import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.userService.User
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
    fun loginService(
        @HeaderMap headers: Map<String, String>,
        @Body body: User.LoginObject
    ): Observable<User.LoginRes>

    //OTP
    @POST(ApiSettings.PATH_OTP)
    fun otpService(
        @HeaderMap header: Map<String, String>,
        @Body body: User.OtpObject
    ): Observable<User.OtpRes>

    // KYC
    @POST(ApiSettings.PATH_KYC)
    fun userAddKyc(
        @HeaderMap header: Map<String, String>,
        @Body param: okhttp3.RequestBody
    ): Observable<User.KycRes>

    /** Register*/
    @POST(ApiSettings.PATH_REGISTER)
    fun registerService(
        @Body body: User.RegisterObject
    ): Observable<User.RegisterRes>

}