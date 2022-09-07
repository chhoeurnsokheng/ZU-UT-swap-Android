package com.zillennium.utswap.module.account.logsScreen

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountLogsBinding
import com.zillennium.utswap.models.logs.Logs
import com.zillennium.utswap.module.account.logsScreen.adapter.LogsAdapter
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData

class LogsActivity :
    BaseMvpActivity<LogsView.View, LogsView.Presenter, ActivityAccountLogsBinding>(),
    LogsView.View {

    override var mPresenter: LogsView.Presenter = LogsPresenter()
    override val layoutResource: Int = R.layout.activity_account_logs

    private var logsList = ArrayList<Logs.AccountLogsLists>()
    private var logsAdapter: LogsAdapter? = null
    private var page: Int = 2
    private var lastPosition = 0
    private var totalPage = 1
    private var isLastPage = false

    override fun initView() {
        super.initView()
        try {
            onCallApi()
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

                val linearLayoutManager = LinearLayoutManager(this@LogsActivity)
                rvLogs.layoutManager = linearLayoutManager
                logsAdapter = LogsAdapter(logsList)
                rvLogs.adapter = logsAdapter

                accountLoadingRefresh()
                clickReadMore()
                loadMoreData()
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }


    override fun accountLogsSuccess(data: Logs.AccountLogsRes) {
        binding.apply {
            mainProgressBar.visibility = View.GONE
            progressBarReadMore.visibility = View.GONE
            accountLogsSwipeRefresh.isRefreshing = false

            if(page == 1){
                logsList.clear()
            }

            totalPage = data.data?.totalPage!!

            if (data.data?.lists?.isNotEmpty() == true) {
                logsList.addAll(data.data?.lists!!)

                layAccountLogsLoading.visibility = View.VISIBLE
                txtReadMore.visibility = View.VISIBLE
//                txtLoading.visibility = View.GONE
            }

            if(page == 2){
                mPresenter.accountLogs(Logs.AccountLogsObject(2), UTSwapApp.instance)
            }

            logsAdapter?.notifyDataSetChanged()
        }
    }

    override fun accountLogsFail(data: Logs.AccountLogsRes) {
        binding.apply {
            mainProgressBar.visibility = View.VISIBLE
            accountLogsSwipeRefresh.isRefreshing = false
        }
    }

    override fun onUserExpiredToken() {
        ClientClearData.clearDataUser()
        startActivity(Intent(this@LogsActivity, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.accountLogs(Logs.AccountLogsObject(1), UTSwapApp.instance)
            }
        }
    }

    private fun accountLoadingRefresh() {
        binding.apply {
            // Swipe refresh to get page
            accountLogsSwipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.primary
                )
            )

            accountLogsSwipeRefresh.setOnRefreshListener {
                page = 2
                logsList.clear()
                progressBarAutoScroll.visibility = View.GONE
                mPresenter.accountLogs(Logs.AccountLogsObject(1), UTSwapApp.instance)
            }
        }
    }

    private fun clickReadMore() {
        binding.apply {
            readMore.setOnClickListener {
                page += 1
                txtReadMore.visibility = View.GONE
                progressBarReadMore.visibility = View.VISIBLE
                mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
            }
        }
    }

    private fun loadMoreData() {
        binding.rvLogs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    lastPosition =
                        (binding.rvLogs.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastPosition == logsList.size - 1  && page < totalPage) {
                        binding.progressBarAutoScroll.visibility = View.VISIBLE
                        page += 1
                        mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
                    }


                }
            }
        })

    }
}