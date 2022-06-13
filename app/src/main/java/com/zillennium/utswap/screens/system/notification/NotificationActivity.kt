package com.zillennium.utswap.screens.system.notification

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNotificationBinding

import com.zillennium.utswap.models.notification.Notification
import com.zillennium.utswap.screens.system.notification.adapter.NotificationAdapter

class NotificationActivity :
    BaseMvpActivity<NotificationView.View, NotificationView.Presenter, ActivityNotificationBinding>(),
    NotificationView.View {

    override var mPresenter: NotificationView.Presenter = NotificationPresenter()
    override val layoutResource: Int = R.layout.activity_notification

    private var notificationList = ArrayList<Notification>()

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener{
                    finish()
                }

                notificationList.add(Notification("Subscription","You have successfully subscribed to UT Pochentong 555...","1 hr(s) ago"))
                notificationList.add(Notification("Server Maintenance","Attention all users, please be informed that both the UT Swap platform and the UT Swap mobile App will be temporarily disabled for maintenance purposes. Thank you.","5 hr(s) ago"))
                notificationList.add(Notification("Trade Executed","Bought 100 UT Muk Kampul 16644 @ $1.29","2 day(s) ago"))
                notificationList.add(Notification("Subscription","You have successfully subscribed to UT Pochentong 555...","2 day(s) ago"))
                notificationList.add(Notification("New UT Alert","The new UT Chhroy Changvar is now available for subscription. Please refer to the UT Chhroy Changvar project page in order to check when your account class will be able to subscribe to the...","3 day(s) ago"))
                notificationList.add(Notification("Subscription","You have successfully subscribed to UT Pochentong 555...","1 hr(s) ago"))
                notificationList.add(Notification("Server Maintenance","Attention all users, please be informed that both the UT Swap platform and the UT Swap mobile App will be temporarily disabled for maintenance purposes. Thank you.","5 hr(s) ago"))
                notificationList.add(Notification("Trade Executed","Bought 100 UT Muk Kampul 16644 @ $1.29","2 day(s) ago"))
                notificationList.add(Notification("Subscription","You have successfully subscribed to UT Pochentong 555...","2 day(s) ago"))
                notificationList.add(Notification("Subscription","You have successfully subscribed to UT Pochentong 555...","1 hr(s) ago"))
                notificationList.add(Notification("Server Maintenance","Attention all users, please be informed that both the UT Swap platform and the UT Swap mobile App will be temporarily disabled for maintenance purposes. Thank you.","5 hr(s) ago"))
                notificationList.add(Notification("Trade Executed","Bought 100 UT Muk Kampul 16644 @ $1.29","2 day(s) ago"))


                val linearLayoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvNotification.layoutManager = linearLayoutManager
                val notificationAdapter = NotificationAdapter(
                    notificationList
                )
                rvNotification.adapter = notificationAdapter

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }


}