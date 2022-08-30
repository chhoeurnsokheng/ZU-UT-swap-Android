package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiTradeImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.tradingList.TradingList
import rx.Subscription

class TransactionsPresenter : BaseMvpPresenterImpl<TransactionsView.View>(),
    TransactionsView.Presenter {

    private var subscriptions: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun onGetMatchingTransaction(
        body: TradingList.TradeMatchingTransactionObj,
        context: Context
    ) {
        subscriptions?.unsubscribe()
        subscriptions = ApiTradeImp().getTradeMatchingTransaction(body,context).subscribe({
            if(it.status == 1){
                mView?.onGetMatchingTransactionSuccess(it)
            }else{
                mView?.onGetMatchingTransactionFail(it)
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