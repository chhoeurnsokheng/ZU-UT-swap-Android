package com.zillennium.utswap.module.main

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiHomeImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import rx.Subscription

class MainPresenter : BaseMvpPresenterImpl<MainView.View>(),
    MainView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onCheckKYCStatus() {
        mContext?.let {
            onCheckKYCStatusSubscription = ApiHomeImp().checkKycStatus(it).subscribe({ it1 ->
                if (it1.status == "1") {
                    mView?.onCheckKYCSuccess(it1)
                } else {
                    mView?.onCheckKYCFail()

                }
            }, { it2 ->
                object : CallbackWrapper(it2, it, arrayListOf()) {
                    override fun onCallbackWrapper(
                        status: ApiManager.NetworkErrorStatus,
                        data: Any
                    ) {
                        mView?.onCheckKYCFail()
                    }
                }

            })

        }
    }

    var onCheckKYCStatusSubscription: Subscription? = null
    override fun onUnSubscript() {
        onCheckKYCStatusSubscription?.unsubscribe()
    }
}