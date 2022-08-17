package com.zillennium.utswap.module.main

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.home.ForceUpdate

class MainView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetForceUpdateSuccess(data:ForceUpdate.ForceUpdateRes)
        fun onGetForceUpdateFailed(data:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun checkForceUpdate(context: Context)
    }
}