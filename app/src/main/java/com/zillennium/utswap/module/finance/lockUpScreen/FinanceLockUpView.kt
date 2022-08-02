package com.zillennium.utswap.module.finance.lockUpScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.lockUpBalance.LockUpBalanceObject

class FinanceLockUpView {
    interface View : BaseMvpView {
        override fun initView()
        fun onPostLockUpBalanceSuccess(dataRes: LockUpBalanceObject.LockUpBalanceRes)
        fun onPostLockBalanceFail()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun postLockUpBalance(type: String)
    }
}