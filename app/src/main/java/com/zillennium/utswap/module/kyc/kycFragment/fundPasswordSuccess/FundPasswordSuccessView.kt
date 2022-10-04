package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordSuccess

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User

class FundPasswordSuccessView {

    interface View: BaseMvpView {

    }

    interface Presenter: BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
    }
}