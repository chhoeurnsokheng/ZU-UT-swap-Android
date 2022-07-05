package com.zillennium.utswap.module.kyc.kycFragment.employmentInfoScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class EmploymentInfoPresenter : BaseMvpPresenterImpl<EmploymentInfoView.View>(),
    EmploymentInfoView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}