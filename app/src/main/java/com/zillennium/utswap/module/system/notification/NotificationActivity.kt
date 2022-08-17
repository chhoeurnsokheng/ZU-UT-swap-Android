package com.zillennium.utswap.module.system.notification

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemNotificationBinding

import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.module.system.notification.adapter.NotificationAdapter

class NotificationActivity :
    BaseMvpActivity<NotificationView.View, NotificationView.Presenter, ActivitySystemNotificationBinding>(),
    NotificationView.View {

    override var mPresenter: NotificationView.Presenter = NotificationPresenter()
    override val layoutResource: Int = R.layout.activity_system_notification

    private val notificationList = ArrayList<NotificationModel>()

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgClose.setOnClickListener {
                    finish()
                }

//                notificationList.add(NotificationModel("Subscription","You have successfully subscribed to UT Pochentong 555...","1 hr(s) ago"))
//                notificationList.add(NotificationModel("Server Maintenance","Attention all users, please be informed that both the UT Swap platform and the UT Swap mobile App will be temporarily disabled for maintenance purposes. Thank you.","5 hr(s) ago"))
//                notificationList.add(NotificationModel("Trade Executed","Bought 100 UT Muk Kampul 16644 @ $1.29","2 day(s) ago"))
//                notificationList.add(NotificationModel("Subscription","You have successfully subscribed to UT Pochentong 555...","2 day(s) ago"))
//                notificationList.add(NotificationModel("New UT Alert","The new UT Chhroy Changvar is now available for subscription. Please refer to the UT Chhroy Changvar project page in order to check when your account class will be able to subscribe to the...","3 day(s) ago"))
//                notificationList.add(NotificationModel("Subscription","You have successfully subscribed to UT Pochentong 555...","1 hr(s) ago"))
//                notificationList.add(NotificationModel("Server Maintenance","Attention all users, please be informed that both the UT Swap platform and the UT Swap mobile App will be temporarily disabled for maintenance purposes. Thank you.","5 hr(s) ago"))
//                notificationList.add(NotificationModel("Trade Executed","Bought 100 UT Muk Kampul 16644 @ $1.29","2 day(s) ago"))
//                notificationList.add(NotificationModel("Subscription","You have successfully subscribed to UT Pochentong 555...","2 day(s) ago"))
//                notificationList.add(NotificationModel("Subscription","You have successfully subscribed to UT Pochentong 555...","1 hr(s) ago"))
//                notificationList.add(NotificationModel("Server Maintenance","Attention all users, please be informed that both the UT Swap platform and the UT Swap mobile App will be temporarily disabled for maintenance purposes. Thank you.","5 hr(s) ago"))
//                notificationList.add(NotificationModel("Trade Executed","Bought 100 UT Muk Kampul 16644 @ $1.29","2 day(s) ago"))
//
//
//                val linearLayoutManager = LinearLayoutManager(UTSwapApp.instance)
//                rvNotification.layoutManager = linearLayoutManager
//                val notificationAdapter = NotificationAdapter(
//                    notificationList
//                )
//                rvNotification.adapter = notificationAdapter

                notificationList.addAll(
                    listOf(
                        NotificationModel(
                            R.drawable.ic_notification,
                            "New",
                            "Z1 Member Ship Announcement",
                            "You have successfully subscribed to UT Pochentong 555",
                            "1 second ago"
                        )
                    )
                )
                notificationList.addAll(
                    listOf(
                        NotificationModel(
                            R.drawable.ic_notification,
                            "",
                            "Server Maintenance",
                            "App will be temporarily disabled for maintenance purposes. Thank you.",
                            "1 second ago"
                        )
                    )
                )
                notificationList.addAll(
                    listOf(
                        NotificationModel(
                            R.drawable.ic_notification,
                            "Earlier",
                            "New UT Alert",
                            "The new UT Chhroy Changvar is now available for subscription. ",
                            "2 second ago"
                        )
                    )
                )
                notificationList.addAll(
                    listOf(
                        NotificationModel(
                            R.drawable.ic_notification,
                            "",
                            "Trade Executed",
                            "Bought 100 UT Muk Kampul 16644 @ \$1.29",
                            "1 second ago"
                        )
                    )
                )
                notificationList.addAll(
                    listOf(
                        NotificationModel(
                            R.drawable.ic_notification,
                            "",
                            "Subscription",
                            "You have successfully subscribed to UT Pochentong 555...",
                            "1 second ago"
                        )
                    )
                )
                notificationList.addAll(
                    listOf(
                        NotificationModel(
                            R.drawable.ic_notification,
                            "",
                            "New UT Alert",
                            "The new UT Chhroy Changvar is now available for subscription.",
                            "2w"
                        )
                    )
                )

                rvNotification.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                val notificationAdapter = NotificationAdapter()
                notificationAdapter.items = notificationList
                rvNotification.adapter = notificationAdapter

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationList.clear()
        binding.rvNotification.adapter?.notifyDataSetChanged()
        binding.unbind()
    }


}