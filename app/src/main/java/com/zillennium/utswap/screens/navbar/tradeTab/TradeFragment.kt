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

                // User
                btnUser.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SettingActivity::class.java)
                    startActivity(intent)
                }

                // Scan QR Code
                btnQrcode.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ScanQRCodeActivity::class.java)
                    startActivity(intent)
                }

                // Search
                btnSearch.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SearchActivity::class.java)
                    startActivity(intent)
                }

                // Notification
                btnNotification.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                    startActivity(intent)
                }

                // Layout Trading
                layTrading.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, TradeExchangeActivity::class.java)
                    startActivity(intent)
                }

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}