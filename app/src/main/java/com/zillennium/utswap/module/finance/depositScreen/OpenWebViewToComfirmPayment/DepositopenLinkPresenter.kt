package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiDepositImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.deposite.DepositObj
import rx.Subscription

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */

class DepositopenLinkPresenter : BaseMvpPresenterImpl<DepositopenLinkView.View>(),
    DepositopenLinkView.Presenter {
    var getQueryOrderSubscription: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getQueryOrder(context: Context, body: DepositObj.DataQueryOrderBody) {
        getQueryOrderSubscription?.unsubscribe()
        getQueryOrderSubscription = ApiDepositImp().getQueryOrder(context, body).subscribe({
            if (it.status ==1){
                mView?.getQueryOrderSuccess(it)
            }

        }, { errorr ->
            object : CallbackWrapper(errorr, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.getQueryOrderFail("Failed")
                }
            }
        })
    }
}