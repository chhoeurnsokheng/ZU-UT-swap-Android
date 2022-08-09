package com.zillennium.utswap.module.account.addPhoneNumberSuccess

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AddPhoneNumberSuccessPresenter : BaseMvpPresenterImpl<AddPhoneNumberSuccessView.View>(),
    AddPhoneNumberSuccessView.Presenter {

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}