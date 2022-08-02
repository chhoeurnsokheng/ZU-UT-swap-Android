package com.zillennium.utswap.module.finance.subscriptionScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.Gson
import com.zillennium.utswap.api.manager.ApiFinanceImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.BaseResponse.BaseResponse
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import rx.Subscription

class FinanceSubscriptionsPresenter : BaseMvpPresenterImpl<FinanceSubscriptionsView.View>(),
    FinanceSubscriptionsView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun postSubscription(body: SubscriptionObject.SubscriptionBody) {
        mContext?.let { it1 ->
            postSubscription = ApiFinanceImp().postSubscription(it1, body).subscribe(
                {
                    val data = Gson().fromJson(it, BaseResponse::class.java)
                    if (data.status == 1) {
                        data.data?.let { subscriptionData ->
                            mView?.onPostSubscriptionSuccess(subscriptionData as ArrayList<SubscriptionObject.SubscriptionList>)
                        }
                    } else {
                        mView?.onPostSubscriptionFail()
                    }
                }, {
                    object : CallbackWrapper(it, it1, arrayListOf()) {
                        override fun onCallbackWrapper(
                            status: ApiManager.NetworkErrorStatus,
                            data: Any
                        ) {
                            mView?.onFail(data.toString())
                        }
                    }


                })

        }
    }

    var postSubscription: Subscription? = null
    override fun onUnSubscript() {
        super.onUnSubscript()
        postSubscription?.unsubscribe()

    }
}