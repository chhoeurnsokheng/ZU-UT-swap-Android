package com.zillennium.utswap.models.ListLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.zillennium.utswap.R;

import java.util.ArrayList;

public class ListLogAdapter extends ArrayAdapter<ListLog> {
    public ListLogAdapter(Context context, ArrayList<ListLog> profileArrayList){
        super(context, R.layout.item_list_setting_log, profileArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListLog profile = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_setting_log, parent, false);
        }

        TextView txt_operationtime = convertView.findViewById(R.id.txt_operationtime);
        TextView txt_action_remark = convertView.findViewById(R.id.txt_action_remark);
        TextView txt_status = convertView.findViewById(R.id.txt_status);

        txt_operationtime.setText(profile.operatingtime);
        txt_action_remark.setText(profile.actionRemark);
        txt_status.setText(profile.status);

        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
