package com.zillennium.utswap.module.main.trade.tradeDetailScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiTradeImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.tradingList.TradingList
import rx.Subscription

class TransactionDetailPresenter : BaseMvpPresenterImpl<TransactionDetailView.View>(),
    TransactionDetailView.Presenter {

    private var subscriptions: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetTransactionDetail(
        body: TradingList.TradeTransactionDetailObj,
        context: Context
    ) {
        subscriptions?.unsubscribe()
        subscriptions = ApiTradeImp().getTransactionDetail(body,context).subscribe({
            if(it.status == 1){
                mView?.onGetTransactionDetailSuccess(it.data!!)
            }else{
                mView?.onGetTransactionDetailFail(it.data!!)
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