package com.zillennium.utswap.screens.project.projectICOScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ProjectICOPresenter : BaseMvpPresenterImpl<ProjectICOView.View>(),
    ProjectICOView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}