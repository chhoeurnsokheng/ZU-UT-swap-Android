package com.zillennium.utswap.module.security.securityFragment.changeFundPassword

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class ChangeFundPasswordPresenter : BaseMvpPresenterImpl<ChangeFundPasswordView.View>(),
    ChangeFundPasswordView.Presenter {

    private var subscriptions: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onSubmitOldFundPassword(body: User.CheckOldFundPasswordObject, context: Context) {
        subscriptions?.unsubscribe()
        subscriptions = ApiUserImp().checkOldFundPassword(body,context).subscribe({
            if(it.status == 1)
            {
                mView?.checkOldFundPasswordSuccess(it)
            }else{
                if(it.message.toString() == "Please sign in"){
                    mView?.onUserExpiredToken()
                }else{
                    mView?.checkOldFundPasswordFail(it)
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