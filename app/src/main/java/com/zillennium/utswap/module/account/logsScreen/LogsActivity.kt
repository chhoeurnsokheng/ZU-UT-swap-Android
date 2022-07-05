package com.zillennium.utswap.module.account.logsScreen

import androidx.recyclerview.widget.LinearLayoutManager
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

    private var logsList = ArrayList<Logs>()
    private var logsAdapter: LogsAdapter? = null

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgClose.setOnClickListener {
                    finish()
                }

                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))
                logsList.add(Logs("2022-06-08 15:05:29","VIA EMAIL","Normal"))

                val linearLayoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvLogs.layoutManager = linearLayoutManager
                logsAdapter = LogsAdapter(logsList)
                rvLogs.adapter = logsAdapter
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}