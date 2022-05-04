package com.zillennium.utswap.screens.setting.languageScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class LanguagePresenter : BaseMvpPresenterImpl<LanguageView.View>(),
    LanguageView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}