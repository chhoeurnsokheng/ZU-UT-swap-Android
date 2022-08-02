package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.province.Province

class IDVerificationView {
    interface View : BaseMvpView {
        override fun initView()
        fun OngetAllProvinceSuccess(data:Province)
        fun OngetAllProvinceFail(data:Province)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getAllProvinceSuccess(context: Context)
    }
}