package com.zillennium.utswap.module.system.notification

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemNotificationBinding

import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.module.system.notification.adapter.NotificationAdapter

class NotificationActivity :
    BaseMvpActivity<NotificationView.View, NotificationView.Presenter, ActivitySystemNotificationBinding>(),
    NotificationView.View {

    override var mPresenter: NotificationView.Presenter = NotificationPresenter()
    override val layoutResource: Int = R.layout.activity_system_notification
    private var mList: ArrayList<NotificationModel.NotificationListData> = arrayListOf()

    private val notificationList: ArrayList<NotificationModel.NotificationListData> = arrayListOf()
    private var notificationAdapter: NotificationAdapter? = null

    override fun initView() {
        super.initView()

        mPresenter.getCustomerSupport(UTSwapApp.instance)
        binding.apply {

            imgClose.setOnClickListener {
                finish()
            }


        }

    }

    override fun onNotificationSuccess(data: NotificationModel.NotificationData) {
        binding.apply {
            mList.clear()

            //notificationList.add(NotificationModel.NotificationListData())
            rvNotification.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            val notificationAdapter = NotificationAdapter(object : NotificationAdapter.OnClickNotificationAdapter{
                override fun clickNotification(action: String) {
                    if (action == "kyc"){

                    }
                 }

            })
            mList.addAll(data.list ?: arrayListOf())


            notificationAdapter.items = mList
//            notificationAdapter.items =
//                notificationList as ArrayList<NotificationModel.NotificationListData>
            rvNotification.adapter = notificationAdapter
        }
    }

    override fun onNotificationFail(data: NotificationModel.NotificationRes) {

    }

    override fun onDestroy() {
        super.onDestroy()
        notificationList.clear()
        binding.rvNotification.adapter?.notifyDataSetChanged()
        binding.unbind()
    }
}