package com.zillennium.utswap.module.main.trade.tradeExchangeScreen

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.models.tradingList.TradingList

class TradeExchangeView {
    interface View : BaseMvpView {
        override fun initView()

        fun onCheckKYCSuccess(data: User.KycRes)
        fun onCheckKYCFail()

        var fetchTradeDetailData: MutableLiveData<TradingList.TradingListSummary>

        fun onCheckFavoriteProjectSuccess(data: TradingList.TradeFavoriteProjectRes)
        fun onCheckFavoriteProjectFail(data: TradingList.TradeFavoriteProjectRes)

        fun addFavoriteProjectSuccess(data: TradingList.TradeAddFavoriteRes)
        fun addFavoriteProjectFail(data: TradingList.TradeAddFavoriteRes)

        fun getAvailableBalanceSuccess(data: TradingList.AvailableBalanceRes)
        fun getAvailableBalanceFail(data: TradingList.AvailableBalanceRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onCheckKYCStatus()

        fun startTradeDetailSocket(marketName: String?)
        fun closeTradeDetailSocket()

        fun onCheckFavoriteProject(body: TradingList.TradeFavoriteProjectObj, context: Context)

        fun addFavoriteProject(body: TradingList.TradeAddFavoriteObj, context: Context)

        fun getAvailableBalance(body: TradingList.AvailableBalanceObj, context: Context)
    }
}