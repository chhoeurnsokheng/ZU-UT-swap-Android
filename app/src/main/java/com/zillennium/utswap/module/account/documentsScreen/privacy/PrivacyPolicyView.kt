package com.zillennium.utswap.module.account.documentsScreen.privacy

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView

/**
 * Created by Sokheng Chhoeurn on 18/7/22.
 * Build in Mac
 */

class PrivacyPolicyView {
    interface View : BaseMvpView {
        override fun initView()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
    }
}