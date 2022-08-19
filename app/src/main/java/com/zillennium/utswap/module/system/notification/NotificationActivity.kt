package com.zillennium.utswap.module.system.notification

import android.content.Intent
import android.graphics.Typeface
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemNotificationBinding

import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.models.project.ProjectList
import com.zillennium.utswap.module.finance.balanceScreen.FinanceBalanceActivity
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.transferScreen.TransferActivity
import com.zillennium.utswap.module.finance.withdrawScreen.WithdrawActivity
import com.zillennium.utswap.module.main.news.NewsFragment
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.system.notification.adapter.NotificationAdapter

class NotificationActivity :
    BaseMvpActivity<NotificationView.View, NotificationView.Presenter, ActivitySystemNotificationBinding>(),
    NotificationView.View {

    override var mPresenter: NotificationView.Presenter = NotificationPresenter()
    override val layoutResource: Int = R.layout.activity_system_notification
    private var mList: ArrayList<NotificationModel.NotificationListData> = arrayListOf()

    private val notificationList: ArrayList<NotificationModel.NotificationListData> = arrayListOf()
    private var notificationAdapter: NotificationAdapter? = null

    private var page = 1
    private var lastPosition = 0
    private var totalItem = 0

    override fun initView() {
        super.initView()
        initToolBar()
        mPresenter.getNotification(UTSwapApp.instance)
        initRecyclerView()
        loadMoreData()
        onSwipeRefresh()

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
            notificationSwipeRefresh.isRefreshing = false
            rvNotification.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            totalItem = data.total_page ?: 0

            if (page == 1) {
                mList.clear()
            }
            mList.addAll(data.list ?: arrayListOf())
            rlProgressBar.visibility = View.GONE
            progressBarNofi.visibility = View.GONE

        }
    }

    override fun onNotificationFail(data: NotificationModel.NotificationRes) {
        binding.apply {
            notificationSwipeRefresh.isRefreshing = false
            progressBarNofi.visibility = View.GONE
            rlProgressBar.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            notificationAdapter =
                NotificationAdapter(object : NotificationAdapter.OnClickNotificationAdapter {
                    override fun clickNotification(action: String, id: String) {

                        if (action.contains("icoPage")) {
                            ProjectInfoActivity.launchProjectInfoActivity(
                                root.context,
                                "4",
                                "project info"

                            )
                        }
                        if (action.contains("Finance/index")) {
                            val intent =
                                Intent(UTSwapApp.instance, FinanceBalanceActivity::class.java)
                            startActivity(intent)
                        }
                        if (action.contains("detail")) {
                            NewsDetailActivity.launchNewsDetailsActivity(
                                root.context,
                                "New Strategies to Strengthen Trade Policy",

                                )
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
                    if (lastPosition == mList.size - 1 && page < totalItem) {
                        binding.progressBarNofi.visibility = View.VISIBLE
                        page++
                        mPresenter.getNotification(UTSwapApp.instance)
                    }

                }
            }
        })

    }

    private fun onSwipeRefresh() {
        binding.apply {
            notificationSwipeRefresh.setOnRefreshListener {
                page = 1
                mPresenter.getNotification(UTSwapApp.instance)
            }
        }
    }

}

