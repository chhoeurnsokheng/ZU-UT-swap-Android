package com.zillennium.utswap.screens.setting.profileScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ProfilePresenter : BaseMvpPresenterImpl<ProfileView.View>(),
    ProfileView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}