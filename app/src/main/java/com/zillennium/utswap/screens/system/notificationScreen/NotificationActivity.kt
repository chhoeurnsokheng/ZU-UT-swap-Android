package com.zillennium.utswap.screens.system.notificationScreen

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemNotificationBinding
import java.io.IOException

class NotificationActivity :
    BaseMvpActivity<NotificationView.View, NotificationView.Presenter, ActivitySystemNotificationBinding>(),
    NotificationView.View {

    override var mPresenter: NotificationView.Presenter = NotificationPresenter()
    override val layoutResource: Int = R.layout.activity_system_notification

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val arrayList = ArrayList<Notification>()
                for (i in 0..19) {
                    arrayList.add(
                        Notification(
                            R.drawable.ut5,
                            "Complete Register",
                            "Zillion United welcomes you to our trading platform. Your trading account is being reviewed by our admin. Please be patient",
                            "6 day ago"
                        )
                    )
                }
                rvNotification.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvNotification.adapter = NotificationAdapter(arrayList)

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}