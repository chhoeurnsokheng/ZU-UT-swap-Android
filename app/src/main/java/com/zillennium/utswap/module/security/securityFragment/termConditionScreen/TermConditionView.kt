package com.zillennium.utswap.module.security.securityFragment.termConditionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView

class TermConditionView {
    interface View : BaseMvpView {
        override fun initView()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
    }
}