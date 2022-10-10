package com.zillennium.utswap.module.finance.balanceScreen.pdfview

import android.content.Context
import android.os.Bundle
import com.pdfview.PDFView
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

/**
 * Created by Sokheng Chhoeurn on 8/10/22.
 * Build in Mac
 */
class PdfViewPresenter: BaseMvpPresenterImpl<PdfViewView.View>(),PdfViewView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        super.initViewPresenter(context, bundle)
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}