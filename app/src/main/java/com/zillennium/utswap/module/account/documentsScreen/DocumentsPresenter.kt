package com.zillennium.utswap.module.account.documentsScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class DocumentsPresenter : BaseMvpPresenterImpl<DocumentsView.View>(),
    DocumentsView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}