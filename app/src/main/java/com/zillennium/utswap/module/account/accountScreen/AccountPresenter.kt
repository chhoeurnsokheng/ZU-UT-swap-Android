package com.zillennium.utswap.module.account.accountScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class AccountPresenter : BaseMvpPresenterImpl<AccountView.View>(),
    AccountView.Presenter {

    private var subscriptions: Subscription? = null
    private var subscriptionUploadProfile: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetUserInfo(context: Context) {
        subscriptions?.unsubscribe()
        subscriptions = ApiUserImp().appSideBarUserInfo(context).subscribe({
            if(it.status == 1)
            {
                mView?.onGetUserInfoSuccess(it.data!!)
            }else{
                mView?.onGetUserInfoFail(it.data!!)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }

    override fun uploadProfile(body: User.AccountUploadProfileObject, context: Context) {
        subscriptionUploadProfile?.unsubscribe()
        subscriptionUploadProfile = ApiUserImp().uploadProfile(body,context).subscribe({
            if(it.status == 1){
                mView?.uploadProfileSuccess(it)
            }else{
                mView?.uploadProfileFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }
            }
        })
    }
}