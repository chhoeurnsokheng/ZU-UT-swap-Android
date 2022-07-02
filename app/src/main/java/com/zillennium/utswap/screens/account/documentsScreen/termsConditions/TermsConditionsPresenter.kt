package com.zillennium.utswap.screens.account.documentsScreen.termsConditions

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TermsConditionsPresenter: BaseMvpPresenterImpl<TermsConditionsView.View>(),
    TermsConditionsView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}