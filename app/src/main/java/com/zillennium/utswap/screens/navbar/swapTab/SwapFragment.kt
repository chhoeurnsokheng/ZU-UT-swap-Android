package com.zillennium.utswap.screens.navbar.swapTab

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarSwapBinding
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity
import com.zillennium.utswap.screens.system.notificationScreen.NotificationActivity
import com.zillennium.utswap.screens.system.scanQRCodeScreen.ScanQRCodeActivity
import com.zillennium.utswap.screens.system.searchScreen.SearchActivity
import java.io.IOException

class SwapFragment :
    BaseMvpFragment<SwapView.View, SwapView.Presenter, FragmentNavbarSwapBinding>(),
    SwapView.View {

    override var mPresenter: SwapView.Presenter = SwapPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_swap

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnUser.setOnClickListener {
                    startActivity(
                        Intent(
                            UTSwapApp.instance,
                            SettingActivity::class.java
                        )
                    )
                }

                btnQrcode.setOnClickListener {
                    startActivity(
                        Intent(
                            UTSwapApp.instance,
                            ScanQRCodeActivity::class.java
                        )
                    )
                }

                btnSearch.setOnClickListener {
                    startActivity(
                        Intent(
                            UTSwapApp.instance,
                            SearchActivity::class.java
                        )
                    )
                }

                btnNotification.setOnClickListener {
                    startActivity(
                        Intent(
                            UTSwapApp.instance,
                            NotificationActivity::class.java
                        )
                    )
                }

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}