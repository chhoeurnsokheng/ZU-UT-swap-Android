package com.zillennium.utswap.module.project.ViewImage

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ImageViewPresenter : BaseMvpPresenterImpl<ImageViewView.View>(),
    ImageViewView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}