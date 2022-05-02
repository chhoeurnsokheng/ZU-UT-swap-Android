package com.zillennium.utswap.screens.setting.logScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BaseDialogActivity;
import com.zillennium.utswap.bases.BaseListViewHeightActivity;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingLogBinding;
import com.zillennium.utswap.models.ListLog.ListLog;
import com.zillennium.utswap.models.ListLog.ListLogAdapter;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {

    ActivitySettingLogBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set Personal ListView
        int[] id_log = {1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10};
        String[] operating_time_log = {"2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34",
                "2022-02-24 15:37:34"};
        String[] action_remark_log = {"LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL",
                "LOGIN VIA EMAIL"};
        String[] status_log = {"Normal",
                "Normal",
                "Normal",
                "Normal",
                "Normal",
                "Normal",
                "Normal",
                "Normal",
                "Normal",
                "Normal"};

        ArrayList<ListLog> logArrayList = new ArrayList<>();

        for(int i=0; i<id_log.length; i++){
            ListLog log = new ListLog(id_log[i], operating_time_log[i], action_remark_log[i], status_log[i]);
            logArrayList.add(log);
        }

        ListLogAdapter logAdapter = new ListLogAdapter(this, logArrayList);

        ListView lvLog = binding.lvLog;
        lvLog.setAdapter(logAdapter);
        lvLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                View view_dialog = getLayoutInflater().inflate(R.layout.dialog_log, null);
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

                BaseDialogActivity.showDialog(LogActivity.this, R.layout.dialog_log);
            }
        });
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvLog);

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(LogActivity.this, ivBack);

    }

}
