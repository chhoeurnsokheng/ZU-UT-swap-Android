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
        @HeaderMap header: Map<String,String>,
        @Body body: User.OtpObject
    ): Observable<User.OtpRes>

    /** Register*/
    @POST(ApiSettings.PATH_REGISTER)
    fun registerService(
        @Body body: User.RegisterObject
    ): Observable<User.RegisterRes>

    /** Forgot Password*/
    @POST(ApiSettings.PATH_FORGOT_PASSWORD)
    fun resetPasswordService(
        @HeaderMap header: Map<String,String>,
        @Body body: User.ForgotPasswordObject
    ): Observable<User.ForgotPasswordRes>

    @POST(ApiSettings.PATH_FORGOT_PASSWORD_VERIFY)
    fun resetPasswordVerify(
        @HeaderMap header: Map<String,String>,
        @Body body: User.ForgotPasswordVerifyObject
    ): Observable<User.ForgotPasswordVerifyRes>

    /** Enter New Password*/
    @POST(ApiSettings.PATH_ENTER_NEW_PASSWORD)
    fun enterNewPassword(
        @HeaderMap header: Map<String,String>,
        @Body body: User.EnterNewPasswordObject
    ): Observable<User.EnterNewPasswordRes>

    /** Change Login Password*/
    @POST(ApiSettings.PATH_CHANGE_LOGIN_PASSWORD)
    fun changeLoginPassword(
        @HeaderMap header: Map<String,String>,
        @Body body: User.ChangeLoginPasswordObject
    ): Observable<User.ChangeLoginPasswordRes>

    /** Change Fund Password*/
    @POST(ApiSettings.PATH_CHANGE_FUND_PASSWORD)
    fun changeFundPassword(
        @HeaderMap header: Map<String,String>,
        @Body body: User.ChangeFundPasswordObject
    ): Observable<User.ChangeFundPasswordRes>
}