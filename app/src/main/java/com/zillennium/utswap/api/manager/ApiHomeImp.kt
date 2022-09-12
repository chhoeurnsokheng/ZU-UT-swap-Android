package com.zillennium.utswap.api.manager

import android.content.Context
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.processor.CompilationUnitContext
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.home.ForceUpdate
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.models.userService.User
import retrofit2.http.Header
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Sokheng Chhoeurn on 4/8/22.
 * Build in Mac
 */
class ApiHomeImp : ApiManager() {
    fun getBanner(context: Context): Observable<BannerObj.Banner> =
        mHomeService.getBanner(
            com.zillennium.utswap.api.Header.getHeader(
                com.zillennium.utswap.api.Header.Companion.AuthType.REQUIRED,
                context
            ),

            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getWishListAndBalance(context: Context): Observable<BannerObj.whistListRes> =
        mHomeService.getWhistListAndBalance(
            com.zillennium.utswap.api.Header.getHeader(
                com.zillennium.utswap.api.Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            )
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun checkForceUpdate(context: Context): Observable<ForceUpdate.ForceUpdateRes> =
        mHomeService.checkForceUpdate().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun checkKycStatus(context: Context): Observable<User.KycRes> =
        mHomeService.checkKYCStatus(
            com.zillennium.utswap.api.Header.getHeader(
                com.zillennium.utswap.api.Header.Companion.AuthType.REQUIRED_TOKEN,
                context
            ),

            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}