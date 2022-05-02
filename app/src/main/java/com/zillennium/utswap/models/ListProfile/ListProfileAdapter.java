package com.zillennium.utswap.models.ListProfile;

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

public class ListProfileAdapter extends ArrayAdapter<ListProfile> {
    public ListProfileAdapter(Context context, ArrayList<ListProfile> profileArrayList){
        super(context, R.layout.item_list_setting_profile, profileArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListProfile profile = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_setting_profile, parent, false);
        }

        TextView txt_title = convertView.findViewById(R.id.txt_title);
        TextView txt_desc = convertView.findViewById(R.id.txt_desc);

        txt_title.setText(profile.title);
        txt_desc.setText(profile.desc);

        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
