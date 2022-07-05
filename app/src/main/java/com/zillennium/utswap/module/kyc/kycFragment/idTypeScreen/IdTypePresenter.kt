package com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class IdTypePresenter : BaseMvpPresenterImpl<IdTypeView.View>(),
    IdTypeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}