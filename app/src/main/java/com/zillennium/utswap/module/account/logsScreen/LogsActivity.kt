package com.zillennium.utswap.module.account.logsScreen

import android.util.Log
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

class LogsActivity :
    BaseMvpActivity<LogsView.View, LogsView.Presenter, ActivityAccountLogsBinding>(),
    LogsView.View {

    override var mPresenter: LogsView.Presenter = LogsPresenter()
    override val layoutResource: Int = R.layout.activity_account_logs

    private var logsList = ArrayList<Logs.AccountLogsLists>()
    private var logsAdapter: LogsAdapter? = null
    private var page: Int = 1
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

            totalPage = data.data?.totalPage!!

            if (data.data?.lists?.isNotEmpty() == true) {
                logsList.addAll(data.data?.lists!!)
                val linearLayoutManager = LinearLayoutManager(this@LogsActivity)
                rvLogs.layoutManager = linearLayoutManager
                logsAdapter = LogsAdapter(logsList)
                rvLogs.adapter = logsAdapter

                layAccountLogsLoading.visibility = View.VISIBLE
                txtReadMore.visibility = View.VISIBLE
//                txtLoading.visibility = View.GONE

            } else {

            }
        }
    }

    override fun accountLogsFail(data: Logs.AccountLogsRes) {
        binding.apply {
            mainProgressBar.visibility = View.VISIBLE
            accountLogsSwipeRefresh.isRefreshing = false
        }
    }

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
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
                page = 1
                logsList.clear()
                progressBarAutoScroll.visibility = View.GONE
                mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
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