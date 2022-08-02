package com.zillennium.utswap.module.finance.subscriptionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject

class FinanceSubscriptionsView {
    interface View : BaseMvpView {
        override fun initView()
        fun onPostSubscriptionSuccess(dataRes: ArrayList<SubscriptionObject.SubscriptionList>)
        fun onPostSubscriptionFail()

    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun postSubscription(body: SubscriptionObject.SubscriptionBody)
    }
}