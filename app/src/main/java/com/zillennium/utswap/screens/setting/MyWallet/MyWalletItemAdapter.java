package com.zillennium.utswap.screens.setting.MyWallet;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.setting.balanceScreen.dialog.BalanceHistoryDetailDialog;

import java.util.ArrayList;

public class MyWalletItemAdapter extends RecyclerView.Adapter<MyWalletItemAdapter.ViewHolder> {

    private ArrayList<MyWalletItem> arrayList = new ArrayList<>();

    public MyWalletItemAdapter(ArrayList<MyWalletItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet_balance_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyWalletItem myWalletItem = arrayList.get(position);

        holder.img.setImageResource(myWalletItem.getImagePath());
        holder.txtDate.setText(myWalletItem.getDate());
        holder.txtName.setText(myWalletItem.getTransactionDetail());
//
        if(myWalletItem.getMoneyIn().equals(" "))
        {
            holder.txtMoney.setText(myWalletItem.getMoneyOut());
        }
        else if(!myWalletItem.getMoneyIn().equals(" "))
        {
            holder.txtMoney.setTextColor(ContextCompat.getColor(holder.container.getContext(), R.color.success));
            holder.img.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.container.getContext(), R.color.success)));
            holder.img.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.container.getContext(), R.color.success)));
            holder.txtMoney.setText(myWalletItem.getMoneyIn());
        }

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

                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//                BalanceHistoryDetailDialog balanceHistoryDetailDialog = BalanceHistoryDetailDialog.newInstance(myWalletItem.getTransaction(),myWalletItem.getTransactionDetail(), myWalletItem.getDate(), myWalletItem.getMoneyOut(), myWalletItem.getBalance(),myWalletItem.getMoneyIn(),myWalletItem.getImagePath());
//                balanceHistoryDetailDialog.show(fragmentTransaction, "My Wallet Item");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtDate;
        private final ImageView img;
        private final View line;
        private final TextView txtMoney;
        private final TextView txtName;
        private final LinearLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDate = itemView.findViewById(R.id.txt_date);
            img = itemView.findViewById(R.id.img_cash);
            line = itemView.findViewById(R.id.line);
            txtMoney = itemView.findViewById(R.id.txt_money);
            txtName = itemView.findViewById(R.id.txt_name);
            container = itemView.findViewById(R.id.container_balance_history);
        }
    }
}
