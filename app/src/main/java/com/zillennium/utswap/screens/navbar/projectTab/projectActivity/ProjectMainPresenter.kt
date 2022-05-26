package com.zillennium.utswap.screens.navbar.projectTab.projectActivity

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInView

class ProjectMainPresenter : BaseMvpPresenterImpl<ProjectMainView.View>(),
    ProjectMainView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}