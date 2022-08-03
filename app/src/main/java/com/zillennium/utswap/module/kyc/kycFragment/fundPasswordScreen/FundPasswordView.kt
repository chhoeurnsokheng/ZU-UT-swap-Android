package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class FundPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun addKycSuccess(data :User.Kyc)
        fun addKycFail(data :String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
         fun addKyc(param: User.Kyc,context: Context)
    }
}