package com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ProjectInfoPresenter : BaseMvpPresenterImpl<ProjectInfoView.View>(),
    ProjectInfoView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}