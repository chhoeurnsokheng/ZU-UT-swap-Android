package com.zillennium.utswap.module.account.addNumberScreen.fragment

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class AddNumberView {
    interface View : BaseMvpView {
        override fun initView()
        fun onAddPhoneNumberSuccess(body: User.AddPhoneNumberRes)
        fun onAddPhoneNumberFail(body: User.AddPhoneNumberRes)
        fun onUserExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onAddPhoneNumber(body:User.AddPhoneNumberObject,context: Context)
    }
}