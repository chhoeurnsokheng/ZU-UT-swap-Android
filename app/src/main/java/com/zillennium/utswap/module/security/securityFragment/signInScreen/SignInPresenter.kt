package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.Gson
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiNotificationImp
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.BaseResponse.BaseResponse
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class SignInPresenter : BaseMvpPresenterImpl<SignInView.View>(),
    SignInView.Presenter {

    private var subscription: Subscription? = null
    private var saveFirebaseTokenSubscription: Subscription? = null

    override fun onUnSubscript() {
        super.onUnSubscript()
        subscription?.unsubscribe()
        saveFirebaseTokenSubscription?.unsubscribe()

    }

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun login(body: User.LoginObject, context: Context) {
        subscription = ApiUserImp().login(body, context).subscribe({
            if (it.status == 1) {
                mView?.loginSuccess(it)
            } else {
                mView?.loginFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun saveFirebaseToken(body: NotificationModel.SubmitFirebaseToken) {
        mContext?.let { mContext ->
            saveFirebaseTokenSubscription =
                ApiNotificationImp().saveFirebaseToken(mContext, body).subscribe({
                    val dataRes = Gson().fromJson(it, BaseResponse::class.java)
                    if (dataRes.status == 1) {
                        mView?.onSaveFirebaseTokenSuccess(dataRes.message.toString())
                    } else {
                        mView?.onSaveFirebaseTokenFail()
                    }

                }, {
                    object : CallbackWrapper(it, mContext, arrayListOf()){
                        override fun onCallbackWrapper(
                            status: ApiManager.NetworkErrorStatus,
                            data: Any
                        ) {
                            mView?.onFail(data)
                        }
                    }


                })

        }
    }
}