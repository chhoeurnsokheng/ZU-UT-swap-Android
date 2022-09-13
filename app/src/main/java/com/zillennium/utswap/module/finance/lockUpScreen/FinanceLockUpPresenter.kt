package com.zillennium.utswap.module.finance.lockUpScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zillennium.utswap.api.manager.ApiFinanceImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.BaseResponse.BaseResponse
import com.zillennium.utswap.models.lockUpBalance.LockUpBalanceObject
import rx.Subscription

class FinanceLockUpPresenter : BaseMvpPresenterImpl<FinanceLockUpView.View>(),
    FinanceLockUpView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun postLockUpBalance(type: Int, page: Int) {
        mContext?.let { it1 ->
            val param = JsonObject()
            param.addProperty("type", type)
            param.addProperty("page", page)
            postLockUpBalanceSubscription = ApiFinanceImp().postLockUpBalance(it1, param).subscribe(
                { lockUpData ->
                    val dataRes =
                        Gson().fromJson(lockUpData, LockUpBalanceObject.LockUpBalance::class.java)
                    if (dataRes.status == 1) {
                        dataRes?.data?.let {
                            mView?.onPostLockUpBalanceSuccess(it)
                        }
                    } else {
                        mView?.onPostLockBalanceFail()
                    }
                }, {
                    object : CallbackWrapper(it, it1, arrayListOf()) {
                        override fun onCallbackWrapper(
                            status: ApiManager.NetworkErrorStatus,
                            data: Any
                        ) {
                            mView?.onFail(data.toString())
                        }
                    }

                })
        }
    }

    var postLockUpBalanceSubscription: Subscription? = null

    override fun onUnSubscript() {
        super.onUnSubscript()
        postLockUpBalanceSubscription?.unsubscribe()
    }
}