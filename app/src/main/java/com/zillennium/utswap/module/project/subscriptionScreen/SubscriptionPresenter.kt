package com.zillennium.utswap.module.project.subscriptionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SubscriptionPresenter : BaseMvpPresenterImpl<SubscriptionView.View>(),
    SubscriptionView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}