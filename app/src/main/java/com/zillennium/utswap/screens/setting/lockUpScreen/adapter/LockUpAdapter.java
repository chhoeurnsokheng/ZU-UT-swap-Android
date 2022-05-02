package com.zillennium.utswap.screens.setting.lockUpScreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.LockUpHistory.LockUpHistory;

import java.util.ArrayList;

public class LockUpAdapter extends RecyclerView.Adapter<LockUpAdapter.ViewHolder> {

    private ArrayList<LockUpHistory> arrayList = new ArrayList<>();
    private LockUpAdapter.OnClickLockUpHistory onClickLockUpHistory;

    public LockUpAdapter(ArrayList<LockUpHistory> arrayList, OnClickLockUpHistory onClickLockUpHistory) {
        this.arrayList = arrayList;
        this.onClickLockUpHistory = onClickLockUpHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_lock_up_history,parent,false);
        return new LockUpAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LockUpHistory lockUpHistory = arrayList.get(position);

        holder.txtAmount.setText(lockUpHistory.getAmount());
        holder.txtDate.setText(lockUpHistory.getDate());

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
            onClickLockUpHistory.clickMe(lockUpHistory);
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

    public interface OnClickLockUpHistory{
        void clickMe(LockUpHistory lockUpHistory);
    }
}
