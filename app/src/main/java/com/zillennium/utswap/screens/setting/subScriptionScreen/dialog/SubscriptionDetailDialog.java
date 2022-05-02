package com.zillennium.utswap.screens.setting.subScriptionScreen.dialog;

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

public class SubscriptionDetailDialog extends DialogFragment {

    private View view;
    private TextView txtNameUt, txtTransactionId, txtPrice, txtVolume, txtValue, txtStart;
    private ImageView imgClose;

    public static SubscriptionDetailDialog newInstance(String nameSub, String value, String date, String price, String volume, String transactionId){
        SubscriptionDetailDialog subscriptionDetailDialog = new SubscriptionDetailDialog();
        Bundle args = new Bundle();
        args.putString("name",nameSub);
        args.putString("value",value);
        args.putString("date",date);
        args.putString("price",price);
        args.putString("volume",volume);
        args.putString("id",transactionId);
        subscriptionDetailDialog.setArguments(args);
        return subscriptionDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_wallet_subscription_history,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        initAction();

        return view;
    }

    public void initView(){
        txtNameUt = view.findViewById(R.id.txt_name_ut_subscript);
        txtPrice = view.findViewById(R.id.txt_price_dialog);
        txtStart = view.findViewById(R.id.txt_start_dialog);
        txtTransactionId = view.findViewById(R.id.txt_transaction_id_dialog);
        txtVolume = view.findViewById(R.id.txt_volume_dialog);
        txtValue = view.findViewById(R.id.txt_value_dialog);
        imgClose = view.findViewById(R.id.img_close);
    }

    public void initAction(){
        imgClose.setOnClickListener(view1 -> {
            dismiss();
        });

        txtNameUt.setText(getArguments().getString("name"));
        txtVolume.setText(getArguments().getString("volume"));
        txtPrice.setText(getArguments().getString("price"));
        txtTransactionId.setText(getArguments().getString("id"));
        txtStart.setText(getArguments().getString("date"));
        txtValue.setText(getArguments().getString("value"));
    }
}
