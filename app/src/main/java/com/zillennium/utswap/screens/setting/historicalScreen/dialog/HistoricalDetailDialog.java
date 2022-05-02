package com.zillennium.utswap.screens.setting.historicalScreen.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.zillennium.utswap.R;

public class HistoricalDetailDialog extends DialogFragment {

    private View view;
    private ImageView img;
    private TextView txtName, txtDate, txtVolume, txtPrice, txtSettlement, txtUtBalance;

    public static HistoricalDetailDialog newInstance(String nameSub, String date, String value, String volume, String price, String settlement, String utBalance){
        HistoricalDetailDialog historicalDetailDialog = new HistoricalDetailDialog();
        Bundle args = new Bundle();
        args.putString("nameSub",nameSub);
        args.putString("date",date);
        args.putString("volume",volume);
        args.putString("price",price);
        args.putString("settlement",settlement);
        args.putString("utBalance",utBalance);
        historicalDetailDialog.setArguments(args);
        return historicalDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_wallet_historical_history,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        initAction();

        return view;
    }

    public void initView(){
        img = view.findViewById(R.id.img_close);
        txtName = view.findViewById(R.id.txt_name_ut);
        txtDate = view.findViewById(R.id.txt_date_dialog);
        txtVolume = view.findViewById(R.id.txt_volume_dialog);
        txtPrice = view.findViewById(R.id.txt_price_dialog);
        txtSettlement = view.findViewById(R.id.txt_settlement_dialog);
        txtUtBalance = view.findViewById(R.id.txt_ut_balance_dialog);
    }

    public void initAction(){
        img.setOnClickListener(view1 -> {
            dismiss();
        });

        txtName.setText(getArguments().getString("nameSub"));
        txtDate.setText(getArguments().getString("date"));
        txtVolume.setText(getArguments().getString("volume"));
        txtPrice.setText(getArguments().getString("price"));
        txtSettlement.setText(getArguments().getString("settlement"));
        txtUtBalance.setText(getArguments().getString("utBalance"));
    }
}
