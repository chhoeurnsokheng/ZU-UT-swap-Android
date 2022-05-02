package com.zillennium.utswap.screens.setting.historicalScreen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.Historical.Historical;

import java.util.ArrayList;

public class HistoricalAdapter extends RecyclerView.Adapter<HistoricalAdapter.ViewHolder> {
    private ArrayList<Historical> arrayList = new ArrayList<>();
    private HistoricalAdapter.OnClickHistoricalHistory onClickHistoricalHistory;

    public HistoricalAdapter(ArrayList<Historical> arrayList, OnClickHistoricalHistory onClickHistoricalHistory) {
        this.arrayList = arrayList;
        this.onClickHistoricalHistory = onClickHistoricalHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_historical_history,parent,false);
        return new HistoricalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Historical historical = arrayList.get(position);

        holder.txtValue.setText(historical.getValue());
        holder.txtDate.setText(historical.getDate());

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
            onClickHistoricalHistory.clickMe(historical);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate, txtValue,txtName;
        private ImageView img;
        private View line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtValue = itemView.findViewById(R.id.txt_value);
            line = itemView.findViewById(R.id.line);
            img = itemView.findViewById(R.id.img_cash);
        }
    }

    public interface OnClickHistoricalHistory{
        void clickMe(Historical historical);
    }
}
