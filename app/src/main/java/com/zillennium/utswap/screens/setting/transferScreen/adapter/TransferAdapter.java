package com.zillennium.utswap.screens.setting.transferScreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.TransferHistory.TransferHistory;

import java.util.ArrayList;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private ArrayList<TransferHistory> arrayList = new ArrayList<>();
    private TransferAdapter.OnclickTransferHistory onclickTransferHistory;

    public TransferAdapter(ArrayList<TransferHistory> arrayList, OnclickTransferHistory onclickTransferHistory) {
        this.arrayList = arrayList;
        this.onclickTransferHistory = onclickTransferHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_transfer_history,parent,false);
        return new TransferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransferHistory transferHistory = arrayList.get(position);

        holder.txtTime.setText(transferHistory.getTime());
        holder.txtAmount.setText(transferHistory.getAmount());
        holder.txtReceiver.setText(transferHistory.getReceiver());

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
            onclickTransferHistory.onClickMe(transferHistory);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAmount, txtReceiver, txtTime;
        private View line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAmount = itemView.findViewById(R.id.txt_amount);
            txtReceiver = itemView.findViewById(R.id.txt_receiver);
            txtTime = itemView.findViewById(R.id.txt_date);
            line = itemView.findViewById(R.id.line);
        }
    }

    public interface OnclickTransferHistory{
        void onClickMe(TransferHistory transferHistory);
    }
}
