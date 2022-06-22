package com.zillennium.utswap.screens.system.notification

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class NotificationPresenter : BaseMvpPresenterImpl<NotificationView.View>(),
    NotificationView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}