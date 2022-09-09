package com.zillennium.utswap.module.finance.withdrawScreen.addBank

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.withdraw.WithdrawObj

class AddBankView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetListAvailableWithdrawBankSuccess(data:WithdrawObj.WithdrawRes)
        fun onGetListAvailableWithdrawBankFailed(data:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getListAvailableWithdrawBank(context: Context)
    }
}