package com.zillennium.utswap.bases.mvp

import android.content.Context

interface BaseMvpView {
    fun getContext(): Context?
    fun initView()
    fun onLoading()
    fun showError(error: String)
    fun onNext(page: Int)
    fun onWillBeHidden()
    fun onWillBeDisplayed()
    fun onRefresh()
    fun onSuccess(any: Any)
    fun onFail(any: Any)
    fun onSetThem(): Int
}