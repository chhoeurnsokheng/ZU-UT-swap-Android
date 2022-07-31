package com.zillennium.utswap.module.account.logsScreen

import android.content.Context
import android.view.View
import android.widget.Toast
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
//                onReadMorePage()
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
            layAccountLogsLoading.visibility = View.GONE
            accountLogsSwipeRefresh.isRefreshing = false
            val linearLayoutManager = LinearLayoutManager(this@LogsActivity)
            rvLogs.layoutManager = linearLayoutManager
            logsAdapter = LogsAdapter()
            logsAdapter!!.items = data!!
            rvLogs.adapter = logsAdapter
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
}