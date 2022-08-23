package com.zillennium.utswap.module.system.notification

import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemNotificationBinding

import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.module.finance.balanceScreen.FinanceBalanceActivity
import com.zillennium.utswap.module.system.notification.adapter.NotificationAdapter
import com.zillennium.utswap.utils.Constants

class NotificationActivity :
    BaseMvpActivity<NotificationView.View, NotificationView.Presenter, ActivitySystemNotificationBinding>(),
    NotificationView.View {

    override var mPresenter: NotificationView.Presenter = NotificationPresenter()
    override val layoutResource: Int = R.layout.activity_system_notification
    private var mList: ArrayList<NotificationModel.NotificationListData> = arrayListOf()
    private var totalPage = 1
    private var lastPosition = 0
    private var page = 1

    private val notificationList: ArrayList<NotificationModel.NotificationListData> = arrayListOf()
    private var notificationAdapter: NotificationAdapter? = null

    override fun initView() {
        super.initView()
        initToolBar()
        mPresenter.readAllNotification()
        loadMoreData()
        initRecyclerView()
        requestData()
        binding.swipeRefresh.setOnRefreshListener {
            page = 1
            requestData()
        }
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
        totalPage = data.total_page
        if (page == 1) {
            mList.clear()
        }
        binding.apply {
            mList.addAll(data.list ?: arrayListOf())
            rlProgressBar.visibility = View.GONE
            pbLoadMore.visibility = View.INVISIBLE
            swipeRefresh.isRefreshing = false
            notificationAdapter?.notifyDataSetChanged()
        }
    }

    private fun requestData() {
        mPresenter.getNotificationLists(this, page)

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
            notificationAdapter =
                NotificationAdapter(object : NotificationAdapter.OnClickNotificationAdapter {
                    override fun clickNotification(notify_type: String, idNotifi: String) {
                        mPresenter.readNotification(idNotifi)
                        when(notify_type) {
                            Constants.NotificationType.DEPOSIT -> {
                                startActivity(Intent(this@NotificationActivity, FinanceBalanceActivity::class.java))
                            }
                            Constants.NotificationType.WITHDRAW -> {
                                startActivity(Intent(this@NotificationActivity, FinanceBalanceActivity::class.java))

                            }
                            Constants.NotificationType.TRANSFER -> {
                                startActivity(Intent(this@NotificationActivity, FinanceBalanceActivity::class.java))

                            }
                            Constants.NotificationType.TRADE -> {
                                startActivity(Intent(this@NotificationActivity, FinanceBalanceActivity::class.java))

                            }
                            Constants.NotificationType.SUBSCRIPTION -> {
                                startActivity(Intent(this@NotificationActivity, FinanceBalanceActivity::class.java))

                            }
                            Constants.NotificationType.PROJECT_INFO -> {

                            }
                            Constants.NotificationType.NEWS_DETAIL -> {

                            }
                            Constants.NotificationType.KYC -> {

                            }

                        }

                    }
                })
            rvNotification.layoutManager = LinearLayoutManager(this@NotificationActivity)
            notificationAdapter?.items = mList
            rvNotification.adapter = notificationAdapter

        }
    }

    private fun loadMoreData() {
        binding.rvNotification.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    lastPosition =
                        (binding.rvNotification.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastPosition == mList.size - 1 && page < totalPage) {
                        binding.pbLoadMore.visibility = View.VISIBLE
                        page++
                        requestData()
                    }


                }
            }
        })

    }


}

