package com.zillennium.utswap.bases.mvp

import android.content.Context
import android.os.Bundle

@Suppress("UNCHECKED_CAST")
abstract class BaseMvpSupportFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> :
        androidx.fragment.app.Fragment(), BaseMvpView {
    protected val DISMISS_TIMEOUT = 500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this@BaseMvpSupportFragment as V)
    }

    override fun getContext(): Context = this.activity!!.baseContext

    protected abstract var mPresenter: T
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onSetThem(): Int {
        return -1
    }
}