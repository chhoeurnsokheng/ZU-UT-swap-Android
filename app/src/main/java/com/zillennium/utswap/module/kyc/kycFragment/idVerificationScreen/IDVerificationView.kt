package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.province.PProvinceObj


class IDVerificationView {
    interface View : BaseMvpView {
        override fun initView()
        fun OngetAllProvinceSuccess(data: PProvinceObj.ProvinceRes)
        fun OngetAllProvinceFail(data:PProvinceObj.ProvinceRes)
        fun OnQueryProvinceSucess(data: PProvinceObj.DistrictRes)
        fun OnQueryCommuneSucess(data: PProvinceObj.CommuneRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getAllProvinceSuccess(context: Context)
        fun queryProvince(context: Context, body: PProvinceObj.BodyProvince)
        fun queryCommune(context: Context, body: PProvinceObj.BodyProvince)

    }
}