package com.zillennium.utswap.module.account.logsScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.logs.Logs
import retrofit2.http.Body

class LogsView {
    interface View : BaseMvpView {
        override fun initView()
        fun accountLogsSuccess(data: Logs.AccountLogsRes)
        fun accountLogsFail(data: Logs.AccountLogsRes)
        fun onUserExpiredToken()
//        override fun onFail(any: Any)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun accountLogs(body: Logs.AccountLogsObject, context: Context)
    }
}