package com.zillennium.utswap.screens.setting.logScreen

import android.widget.AdapterView.OnItemClickListener
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseDialogActivity
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingLogBinding
import com.zillennium.utswap.models.ListLog.ListLog
import com.zillennium.utswap.models.ListLog.ListLogAdapter
import java.io.IOException

class LogActivity :
    BaseMvpActivity<LogView.View, LogView.Presenter, ActivitySettingLogBinding>(),
    LogView.View {

    override var mPresenter: LogView.Presenter = LogPresenter()
    override val layoutResource: Int = R.layout.activity_setting_log

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Personal ListView

                // Set Personal ListView
                val idLog = intArrayOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10
                )
                val operatingTimeLog = arrayOf(
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34",
                    "2022-02-24 15:37:34"
                )
                val actionRemarkLog = arrayOf(
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL",
                    "LOGIN VIA EMAIL"
                )
                val statusLog = arrayOf(
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal",
                    "Normal"
                )

                val logArrayList = ArrayList<ListLog>()

                for (i in idLog.indices) {
                    val log = ListLog(
                        idLog[i],
                        operatingTimeLog[i],
                        actionRemarkLog[i],
                        statusLog[i]
                    )
                    logArrayList.add(log)
                }

                val logAdapter = ListLogAdapter(UTSwapApp.instance, logArrayList)

                val lvLog = binding.lvLog
                lvLog.adapter = logAdapter
                lvLog.onItemClickListener =
                    OnItemClickListener { adapterView, view, position, l -> //                View view_dialog = getLayoutInflater().inflate(R.layout.dialog_log, null);
                        //
                        //                TextView txt_operation_time = LogActivity.this.findViewById(R.id.txt_operation_time);
                        //                TextView txt_action_type = view_dialog.findViewById(R.id.txt_action_type);
                        //                TextView txt_action_remark = view_dialog.findViewById(R.id.txt_action_remark);
                        //                TextView txt_action_ip = view_dialog.findViewById(R.id.txt_action_ip);
                        //                TextView txt_action_address = view_dialog.findViewById(R.id.txt_action_address);
                        //                TextView txt_status = LogActivity.this.findViewById(R.id.txt_status);
                        //
                        //                txt_operation_time.setText(operating_time_log[position]);
                        //                txt_action_type.setText(operating_time_log[position]);
                        //                txt_action_remark.setText(operating_time_log[position]);
                        //                txt_action_ip.setText(operating_time_log[position]);
                        //                txt_action_address.setText(operating_time_log[position]);
                        //                txt_status.setText(operating_time_log[position]);
                        BaseDialogActivity.showDialog(this@LogActivity, R.layout.dialog_log)
                    }
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvLog)

                // Set Passed Back

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}