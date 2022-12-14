package com.zillennium.utswap.module.account.referralInformationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ReferralInformationPresenter : BaseMvpPresenterImpl<ReferralInformationView.View>(),
    ReferralInformationView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}