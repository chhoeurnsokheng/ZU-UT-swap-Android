package com.zillennium.utswap.screens.setting.depositScreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.PaymentMethod.CustomItem;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    public CustomAdapter(@NonNull Context context, ArrayList<CustomItem> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner,parent,false);
        }
        CustomItem item = (CustomItem) getItem(position);
        ImageView spinnerImg = convertView.findViewById(R.id.img_logo_payment_spinner);
        TextView spinnerTxt = convertView.findViewById(R.id.txt_payment_method_spinner);
        if(item!=null){
            spinnerImg.setImageResource(item.getSpinnerItemImage());
            spinnerTxt.setText(item.getSpinnerItemName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_payment_method,parent,false);
        }
        CustomItem item = (CustomItem) getItem(position);
        ImageView dropDownImg = convertView.findViewById(R.id.img_logo_payment);
        TextView dropDownTxt = convertView.findViewById(R.id.txt_payment_method);
        if(item!=null){
            dropDownImg.setImageResource(item.getSpinnerItemImage());
            dropDownTxt.setText(item.getSpinnerItemName());
        }
        return convertView;
    }
}
