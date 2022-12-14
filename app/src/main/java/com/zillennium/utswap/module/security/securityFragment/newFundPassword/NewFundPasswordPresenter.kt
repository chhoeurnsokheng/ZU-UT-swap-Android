package com.zillennium.utswap.module.security.securityFragment.newFundPassword

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class NewFundPasswordPresenter : BaseMvpPresenterImpl<NewFundPasswordView.View>(),
    NewFundPasswordView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onSubmitNewPassword(body: User.ChangeFundPasswordObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().changeFundPassword(body,context).subscribe({
            if(it.status == 1){
                mView?.onChangePasswordSuccess(it)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.onChangePasswordFail(it)
                }
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