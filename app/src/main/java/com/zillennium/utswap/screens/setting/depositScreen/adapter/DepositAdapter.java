package com.zillennium.utswap.screens.setting.depositScreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.DepositHistory.DepositHistory;

import java.util.ArrayList;

public class DepositAdapter extends RecyclerView.Adapter<DepositAdapter.ViewHolder> {

    private ArrayList<DepositHistory> arrayList = new ArrayList<>();
    private DepositAdapter.OnclickDepositHistory onclickDepositHistory;

    public DepositAdapter(ArrayList<DepositHistory> arrayList, OnclickDepositHistory onclickDepositHistory) {
        this.arrayList = arrayList;
        this.onclickDepositHistory = onclickDepositHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_deposit_history,parent,false);
        return new DepositAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DepositHistory depositHistory = arrayList.get(position);

        holder.img.setImageResource(depositHistory.getImagePath());
        holder.txtAmount.setText(depositHistory.getAmount());
        holder.txtDate.setText(depositHistory.getRechargeTime());
        holder.txtStatus.setText(depositHistory.getStatus());

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickDepositHistory.onClickMe(depositHistory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private View line;
        private TextView txtDate, txtAmount, txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_success);
            line = itemView.findViewById(R.id.line);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtStatus = itemView.findViewById(R.id.txt_status);
            txtAmount = itemView.findViewById(R.id.txt_amount);
        }
    }

    public interface OnclickDepositHistory{
        void onClickMe(DepositHistory depositHistory);
    }
}
