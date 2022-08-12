package com.zillennium.utswap.module.account.logsScreen

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var logsList = ArrayList<Logs.AccountLogsData>()
    private var logsAdapter: LogsAdapter? = null
    private var page: Int? = 1
    override fun initView() {
        super.initView()
        try {
            binding.apply {
                mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
                imgClose.setOnClickListener {
                    finish()
                }

                onCallApi()
                accountLoadingRefresh()
                clickReadMore()
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }


    override fun accountLogsSuccess(data: ArrayList<Logs.AccountLogsData>?) {
        binding.apply {
            mainProgressBar.visibility = View.GONE
            progressBarReadMore.visibility = View.GONE
            layAccountLogsLoading.visibility = View.VISIBLE
            accountLogsSwipeRefresh.isRefreshing = false


            if (data!!.isNotEmpty()) {
                logsList.addAll(data)
                val linearLayoutManager = LinearLayoutManager(this@LogsActivity)
                rvLogs.layoutManager = linearLayoutManager
                logsAdapter = LogsAdapter()
                logsAdapter!!.items = logsList
                rvLogs.adapter = logsAdapter

                //Add more data page
                page = page!! + 1
                txtReadMore.visibility = View.VISIBLE
                txtLoading.visibility = View.GONE

            } else {

                layAccountLogsLoading.visibility = View.GONE
                txtEndData.visibility = View.VISIBLE
            }
        }
    }

    override fun accountLogsFail(body: Logs.AccountLogsRes) {
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
                txtEndData.visibility = View.GONE
                page = 1
                logsList.clear()
                mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
            }
        }
    }

    private fun clickReadMore() {
        binding.apply {
            readMore.setOnClickListener {
                txtReadMore.visibility = View.GONE
                txtLoading.visibility = View.VISIBLE
                progressBarReadMore.visibility = View.VISIBLE
                mPresenter.accountLogs(Logs.AccountLogsObject(page), UTSwapApp.instance)
            }
        }
    }
}