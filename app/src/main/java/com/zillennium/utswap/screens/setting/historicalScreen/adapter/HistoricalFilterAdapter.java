package com.zillennium.utswap.screens.setting.historicalScreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.Subscription.Subscription;

import java.util.List;

public class HistoricalFilterAdapter extends ArrayAdapter {

    public HistoricalFilterAdapter(@NonNull Context context ,@NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_historical_filter,parent,false);
        }
        Subscription item = (Subscription) getItem(position);
        TextView dropDownTxt = convertView.findViewById(R.id.txt_name_ut_historical);
        if(item!=null){
            dropDownTxt.setText(item.getStr());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_historical_filter_dropdown,parent,false);
        }
        Subscription item = (Subscription) getItem(position);
        TextView dropDownTxt = convertView.findViewById(R.id.txt_historical_dropdown);
        if(item!=null){
            dropDownTxt.setText(item.getStr());
        }
        return convertView;
    }

}
