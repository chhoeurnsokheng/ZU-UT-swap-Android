package com.zillennium.utswap.module.account.customerSupportScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiCustomerSupportImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.customerSupport.CustomerSupport
import rx.Subscription

class CustomerSupportPresenter : BaseMvpPresenterImpl<CustomerSupportView.View>(),
    CustomerSupportView.Presenter {

    private var subscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getCustomerSupport() {
        subscription?.unsubscribe()
        subscription = ApiCustomerSupportImp().customerSupport().subscribe({
            if (it.status == 1){
                mView?.onGetCustomerSupportSuccess(it.data as CustomerSupport.CustomerSupportData)
            }
            else{
                mView?.onFail(it)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }

            }
        })
    }
}