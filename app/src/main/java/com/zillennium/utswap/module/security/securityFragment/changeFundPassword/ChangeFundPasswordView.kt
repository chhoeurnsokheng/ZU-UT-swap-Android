package com.zillennium.utswap.module.security.securityFragment.changeFundPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class ChangeFundPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun checkOldFundPasswordSuccess(data: User.CheckOldFundPasswordRes)
        fun checkOldFundPasswordFail(data: User.CheckOldFundPasswordRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onSubmitOldFundPassword(body: User.CheckOldFundPasswordObject,context: Context)
    }
}