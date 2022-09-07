package com.zillennium.utswap.module.security.securityFragment.newFundPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class NewFundPasswordView {
    interface View : BaseMvpView {
        override fun initView()
        fun onChangePasswordSuccess(body: User.ChangeFundPasswordRes)
        fun onChangePasswordFail(body: User.ChangeFundPasswordRes)
        fun onUserExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onSubmitNewPassword(body: User.ChangeFundPasswordObject, context: Context)
    }
}