package com.zillennium.utswap.screens.project.projecrDetailScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ProjectDetailPresenter : BaseMvpPresenterImpl<ProjectDetailView.View>(),
    ProjectDetailView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}