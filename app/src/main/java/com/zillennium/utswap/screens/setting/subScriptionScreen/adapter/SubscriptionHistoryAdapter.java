package com.zillennium.utswap.screens.setting.subScriptionScreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.SubscriptionHistory.SubscriptionHistory;

import java.util.ArrayList;

public class SubscriptionHistoryAdapter extends RecyclerView.Adapter<SubscriptionHistoryAdapter.ViewHolder> {

    private ArrayList<SubscriptionHistory> arrayList = new ArrayList<>();
    private SubscriptionHistoryAdapter.OnclickSubscriptionHistory onclickSubscriptionHistory;

    public SubscriptionHistoryAdapter(ArrayList<SubscriptionHistory> arrayList, OnclickSubscriptionHistory onclickSubscriptionHistory) {
        this.arrayList = arrayList;
        this.onclickSubscriptionHistory = onclickSubscriptionHistory;
    }

    @NonNull
    @Override
    public SubscriptionHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_subscription_history,parent,false);
        return new SubscriptionHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionHistoryAdapter.ViewHolder holder, int position) {
        SubscriptionHistory subscriptionHistory = arrayList.get(position);

        holder.txtNameSub.setText(subscriptionHistory.getNameSub());
        holder.txtValue.setText(subscriptionHistory.getValue());
        holder.txtDate.setText(subscriptionHistory.getDate());

        if (arrayList.size() == 1) {
            holder.line.setVisibility(View.GONE);
        }else {
            if (arrayList.size() - 1 == position){
                holder.line.setVisibility(View.GONE);
            } else if (0 == position) {
                holder.itemView.setBackgroundResource(R.drawable.card_view_whtie_border_top);
                holder.line.setVisibility(View.VISIBLE);
            } else {
                holder.itemView.setBackgroundResource(R.color.white);
                holder.line.setVisibility(View.VISIBLE);
            }
        }

        holder.itemView.setOnClickListener(view -> {
            onclickSubscriptionHistory.onClickMe(subscriptionHistory);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameSub, txtValue, txtDate;
        private View line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameSub = itemView.findViewById(R.id.txt_nameSub);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtValue = itemView.findViewById(R.id.txt_value);
            line = itemView.findViewById(R.id.line);
        }
    }

    public interface OnclickSubscriptionHistory{
        void onClickMe(SubscriptionHistory subscriptionHistory);
    }
}
