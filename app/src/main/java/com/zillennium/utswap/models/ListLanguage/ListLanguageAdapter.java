package com.zillennium.utswap.models.ListLanguage;

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

import java.util.ArrayList;

public class ListLanguageAdapter extends ArrayAdapter<ListLanguage> {
    public ListLanguageAdapter(Context context, ArrayList<ListLanguage> accountArrayList){
        super(context, R.layout.item_list_setting_language, accountArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListLanguage language = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_setting_language, parent, false);
        }

        TextView txt_title = convertView.findViewById(R.id.txt_title);
        ImageView iv_icon = convertView.findViewById(R.id.iv_icon);
        ImageView icon_right = convertView.findViewById(R.id.icon_right);

        txt_title.setText(language.title);
        iv_icon.setImageResource(language.icon);

        if(language.selected == true){
            icon_right.setImageResource(R.drawable.ic_circle_check_regular);
        }

        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
