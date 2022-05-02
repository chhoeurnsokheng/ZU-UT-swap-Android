package com.zillennium.utswap.models.ListLockScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.zillennium.utswap.R;

import java.util.ArrayList;

public class ListLockScreenAdapter extends ArrayAdapter<ListLockScreen> {
    public ListLockScreenAdapter(Context context, ArrayList<ListLockScreen> lockScreenArrayList){
        super(context, R.layout.item_list_setting_menu, lockScreenArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListLockScreen lockScreen = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_setting_lockscreen, parent, false);
        }

        TextView txt_title = convertView.findViewById(R.id.txt_title);
        txt_title.setText(lockScreen.title);

        if(lockScreen.selected == true){

            LinearLayout lay_item = convertView.findViewById(R.id.lay_item);
            ImageView iv_icon = convertView.findViewById(R.id.iv_icon);

            lay_item.setBackgroundResource(R.drawable.bg_lockscreen_selected);
            iv_icon.setColorFilter(convertView.getResources().getColor(R.color.white));
            txt_title.setTextColor(convertView.getResources().getColor((R.color.white)));

        }

        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
