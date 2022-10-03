package com.zillennium.utswap.module.finance.transferScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

/**
 * Created by Sokheng Chhoeurn on 3/10/22.
 * Build in Mac
 */

class  TransferActivityReviewPresenter : BaseMvpPresenterImpl<TransferActivityReviewView.View>(),
    TransferActivityReviewView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
    }