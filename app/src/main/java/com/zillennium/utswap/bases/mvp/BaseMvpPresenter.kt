package com.zillennium.utswap.bases.mvp

import android.content.Context
import android.os.Bundle

/**
 * Created by nemo on 9/28/2017.
 * Copyright © 2022 Z1finance. All rights reserved.
 */

interface BaseMvpPresenter<in V : BaseMvpView> {
    fun initViewPresenter(context: Context, bundle: Bundle?)
    fun onWillBeHidden()
    fun onWillBeDisplayed()
    fun onRefresh()
    fun attachView(view: V)
    fun detachView()
    fun onUnSubscript()
}