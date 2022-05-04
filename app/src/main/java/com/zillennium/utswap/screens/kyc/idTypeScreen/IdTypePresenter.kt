package com.zillennium.utswap.screens.kyc.idTypeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.simple.SimpleView

class IdTypePresenter : BaseMvpPresenterImpl<IdTypeView.View>(),
    IdTypeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}