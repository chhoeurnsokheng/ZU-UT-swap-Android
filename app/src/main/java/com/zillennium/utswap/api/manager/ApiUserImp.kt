package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.user.LoginVerifyLoginParam
import com.zillennium.utswap.models.user.VerifiyCode
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author chhoeurnsokheng
 * Created 7/7/22 at 2:48 PM
 * By Mac
 */

class ApiUserImp:ApiManager() {

    fun loginVerifyCode(context: Context,param: LoginVerifyLoginParam): Observable<VerifiyCode> =
        mUserService.loginVerifyCode(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            param
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}