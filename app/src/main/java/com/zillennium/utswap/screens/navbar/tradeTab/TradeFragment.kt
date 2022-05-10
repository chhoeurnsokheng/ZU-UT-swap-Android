package com.zillennium.utswap.screens.navbar.tradeTab

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity
import com.zillennium.utswap.screens.system.notificationScreen.NotificationActivity
import com.zillennium.utswap.screens.system.scanQRCodeScreen.ScanQRCodeActivity
import com.zillennium.utswap.screens.system.searchScreen.SearchActivity
import com.zillennium.utswap.screens.trade.tradeExchangeScreen.TradeExchangeActivity
import java.io.IOException

class TradeFragment :
    BaseMvpFragment<TradeView.View, TradeView.Presenter, FragmentNavbarTradeBinding>(),
    TradeView.View {

    override var mPresenter: TradeView.Presenter = TradePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_trade

    override fun initView() {
        super.initView()
        try {
            binding.apply {


            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}