package com.zillennium.utswap.module.account.addNumberScreen.fragment

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import rx.Subscription

class AddNumberPresenter : BaseMvpPresenterImpl<AddNumberView.View>(),
    AddNumberView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onAddPhoneNumber(body: User.AddPhoneNumberObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiUserImp().addPhoneNumber(body,context).subscribe({
            if(it.status == 1){
                mView?.onAddPhoneNumberSuccess(it)
            }else{
                mView?.onAddPhoneNumberFail(it)
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