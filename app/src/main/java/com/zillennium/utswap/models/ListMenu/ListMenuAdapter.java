package com.zillennium.utswap.models.ListMenu;

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

public class ListMenuAdapter extends ArrayAdapter<ListMenu> {
    public ListMenuAdapter(Context context, ArrayList<ListMenu> accountArrayList){
        super(context, R.layout.item_list_setting_menu, accountArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListMenu account = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_setting_menu, parent, false);
        }

        TextView txt_title = convertView.findViewById(R.id.txt_title);
        ImageView iv_icon = convertView.findViewById(R.id.iv_icon);

        txt_title.setText(account.title);
        iv_icon.setImageResource(account.icon);

        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
