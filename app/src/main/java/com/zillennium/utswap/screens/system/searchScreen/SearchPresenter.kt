package com.zillennium.utswap.screens.system.searchScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SearchPresenter : BaseMvpPresenterImpl<SearchView.View>(),
    SearchView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}