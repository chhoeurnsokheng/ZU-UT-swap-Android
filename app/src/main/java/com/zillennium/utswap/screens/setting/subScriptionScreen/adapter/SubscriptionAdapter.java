package com.zillennium.utswap.screens.setting.subScriptionScreen.adapter;

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

public class SubscriptionAdapter extends ArrayAdapter {
    public SubscriptionAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_in_subscription,parent,false);
        }
        Subscription item = (Subscription) getItem(position);
        TextView dropDownTxt = convertView.findViewById(R.id.txt_name_ut_spinner);
        if(item!=null){
            dropDownTxt.setText(item.getStr());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_in_subscription,parent,false);
        }
        Subscription item = (Subscription) getItem(position);
        TextView dropDownTxt = convertView.findViewById(R.id.txt_name_ut);
        if(item!=null){
            dropDownTxt.setText(item.getStr());
        }
        return convertView;
    }
}
