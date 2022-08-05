package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.userService.User
import okhttp3.RequestBody
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author chhoeurnsokheng
 * Created 7/7/22 at 2:48 PM
 * By Mac
 */

class ApiUserImp : ApiManager() {

    fun login(body: User.LoginObject, context: Context): Observable<User.LoginRes> =
        mUserService.loginService(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun otp(body: User.OtpObject, context: Context): Observable<User.OtpRes> =
        mUserService.otpService(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addKyc(body: User.Kyc,context: Context): Observable<User.KycRes> = mUserService.userAddKyc(
        Header.getHeader(Header.Companion.AuthType.REQUIRED_TOKEN, context),
        body
    )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun register(body: User.RegisterObject): Observable<User.RegisterRes> =
        mUserService.registerService(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    /** Forgot Password*/
    fun resetPassword(body: User.ForgotPasswordObject, context: Context): Observable<User.ForgotPasswordRes> =
        mUserService.resetPasswordService(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun resetPasswordVerify(body: User.ForgotPasswordVerifyObject, context: Context): Observable<User.ForgotPasswordVerifyRes> =
        mUserService.resetPasswordVerify(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun enterNewPassword(body: User.EnterNewPasswordObject,context: Context): Observable<User.EnterNewPasswordRes> =
        mUserService.enterNewPassword(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}