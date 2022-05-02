package com.zillennium.utswap.screens.setting.MyWallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;

import java.util.ArrayList;

public class MyWalletGroupAdapter extends RecyclerView.Adapter<MyWalletGroupAdapter.ViewHolder> {
    private ArrayList<MyWalletGroup> arrayList = new ArrayList<>();

    public <onclickBalanceHistory> MyWalletGroupAdapter(ArrayList<MyWalletGroup> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyWalletGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_balance_group,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWalletGroupAdapter.ViewHolder holder, int position) {
        MyWalletGroup myWalletGroup = arrayList.get(position);

        holder.txtDayGroup.setText(myWalletGroup.getSelectDayGroup());

        MyWalletItemAdapter myWalletItemAdapter = new MyWalletItemAdapter(myWalletGroup.getSelectItemGroup());
        holder.rcItemGroupDay.setAdapter(myWalletItemAdapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtDayGroup;
        private final RecyclerView rcItemGroupDay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDayGroup = itemView.findViewById(R.id.txt_day_group);
            rcItemGroupDay = itemView.findViewById(R.id.rc_item_group_day);


        }
    }
}
