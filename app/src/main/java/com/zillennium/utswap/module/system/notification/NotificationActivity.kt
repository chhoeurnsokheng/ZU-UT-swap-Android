package com.zillennium.utswap.module.system.notification

import android.graphics.Typeface
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
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
        initToolBar()
        mPresenter.getNotificationLists(UTSwapApp.instance)
        mPresenter.readAllNotification()
        initRecyclerView()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolBar.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_black)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolBar.apply {
            tbTitle.setText(R.string.notifications)
            tbTitle.typeface = Typeface.DEFAULT_BOLD
            tbTitle.setTextColor(ContextCompat.getColor(this@NotificationActivity, R.color.black))
            tb.setNavigationOnClickListener {
                finish()
            }


        }
    }

    override fun onNotificationSuccess(data: NotificationModel.NotificationData) {
        binding.apply {
            mList.clear()
            rvNotification.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            mList.addAll(data.list ?: arrayListOf())
            rlProgressBar.visibility = View.GONE
        }
    }

    override fun onNotificationFail(data: NotificationModel.NotificationRes) {

    }

    override fun onReadNotificationSuccess(message: String) {
        Log.d("Read", message)

    }

    override fun onReadNotificationFail() {
    }

    override fun onReadAllNotificationSuccess(message: String) {
        Log.d("Read", message)
        SessionVariable.BADGE_NUMBER.value = ""

    }

    override fun onReadAllNotificationFail() {
    }

    private fun initRecyclerView() {
        binding.apply {
             notificationAdapter = NotificationAdapter(object : NotificationAdapter.OnClickNotificationAdapter{
                override fun clickNotification(action: String, idNotifi: String) {
                    mPresenter.readNotification(idNotifi)

                }
            })
            rvNotification.layoutManager = LinearLayoutManager(this@NotificationActivity)
            notificationAdapter?.items = mList
            rvNotification.adapter = notificationAdapter

        }
    }



    }

