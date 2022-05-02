package com.zillennium.utswap.bases.mvp

import android.content.Context
import android.os.Bundle

open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mBundle: Bundle? = null
    protected var mContext: Context? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {

    }

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
    override fun onWillBeHidden() {

    }

    override fun onWillBeDisplayed() {

    }

    override fun onRefresh() {

    }

    override fun onUnSubscript() {

    }
}