package com.zillennium.utswap.screens.setting.withdrawalScreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.WithDrawalHistory.WithDrawalHistory;

import java.util.ArrayList;

public class WithDrawalAdapter extends RecyclerView.Adapter<WithDrawalAdapter.ViewHolder> {

    private ArrayList<WithDrawalHistory> arrayList = new ArrayList<>();
    private WithDrawalAdapter.OnclickWithDrawHistory onclickWithDrawHistory;

    public WithDrawalAdapter(ArrayList<WithDrawalHistory> arrayList, OnclickWithDrawHistory onclickWithDrawHistory) {
        this.arrayList = arrayList;
        this.onclickWithDrawHistory = onclickWithDrawHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_withdraw_history,parent,false);
        return new WithDrawalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WithDrawalHistory withDrawalHistory = arrayList.get(position);

        holder.txtAmount.setText(withDrawalHistory.getAmount());
        holder.txtDate.setText(withDrawalHistory.getTime());

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
            onclickWithDrawHistory.onClickMe(withDrawalHistory);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAmount, txtDate;
        private View line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAmount = itemView.findViewById(R.id.txt_amount);
            txtDate = itemView.findViewById(R.id.txt_date);
            line = itemView.findViewById(R.id.line);
        }
    }

    public interface OnclickWithDrawHistory{
        void onClickMe(WithDrawalHistory withDrawalHistory);
    }
}
