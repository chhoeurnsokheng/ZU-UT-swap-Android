package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author chhoeurnsokheng
 * Created 7/7/22 at 2:48 PM
 * By Mac
 */

class ApiUserImp:ApiManager() {
    fun login(body: Any, context: Context): Observable<Any> =
        mUserService.loginService(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            body
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}