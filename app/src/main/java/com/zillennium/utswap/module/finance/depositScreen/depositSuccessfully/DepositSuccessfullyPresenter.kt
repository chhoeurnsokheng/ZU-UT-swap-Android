package com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.api.manager.ApiDepositImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.deposite.DepositObj
import rx.Subscription

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */
class DepositSuccessfullyPresenter:BaseMvpPresenterImpl<DepositSuccessfullyView.View>(),
    DepositSuccessfullyView.Presenter {
    var getQueryOrderSubscription:Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        super.initViewPresenter(context, bundle)
        mBundle = bundle
        mContext = context
        mView?.initView()
    }


}