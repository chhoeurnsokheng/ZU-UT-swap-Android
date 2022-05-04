package com.zillennium.utswap.screens.kyc.declarationScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class DeclarationPresenter : BaseMvpPresenterImpl<DeclarationView.View>(),
    DeclarationView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}