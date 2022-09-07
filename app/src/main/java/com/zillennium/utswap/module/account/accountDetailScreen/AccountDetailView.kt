package com.zillennium.utswap.module.account.accountDetailScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class AccountDetailView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetUserInfoDetailSuccess(data: User.AppSideBarData)
        fun onGetUserInfoDetailFail(data: User.AppSideBarData)
        fun userExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetUserInfoDetail(context: Context)
    }
}