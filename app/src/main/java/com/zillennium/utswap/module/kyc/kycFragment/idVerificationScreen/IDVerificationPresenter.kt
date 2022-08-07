package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProvincesImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.province.PProvinceObj
import rx.Subscription

class IDVerificationPresenter : BaseMvpPresenterImpl<IDVerificationView.View>(),
    IDVerificationView.Presenter {
    private var subscription: Subscription? = null
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getAllProvinceSuccess(context: Context) {
       subscription?.unsubscribe()
        subscription = ApiProvincesImp().getAllProvinces(context).subscribe({

                mView?.OngetAllProvinceSuccess(it)

        },{
            object :CallbackWrapper(it,UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data.toString())
                }

            }
        })
    }

    override fun queryProvince(context: Context, body: PProvinceObj.BodyProvince) {
        subscription?.unsubscribe()
        subscription = ApiProvincesImp().queryProvince(context,body).subscribe({

                mView?.OnQueryProvinceSucess(it)

        },{
            object :CallbackWrapper(it,UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data)
                }

            }
        })
    }

    override fun queryCommune(context: Context, body: PProvinceObj.BodyProvince) {
        subscription?.unsubscribe()
        subscription = ApiProvincesImp().queryCommune(context,body).subscribe({
            mView?.OnQueryCommuneSucess(it)

        },{
            object :CallbackWrapper(it,UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                    mView?.onFail(data)
                }

            }
        })
    }
}