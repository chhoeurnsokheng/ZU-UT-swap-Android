package com.zillennium.utswap.screens.setting.withdrawalScreen.dialog;

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

public class WithdrawHistoryDetailDialog extends DialogFragment {

    private View view;
    private TextView txtTransactionId, txtTime, txtAmount, txtAmountArrival, txtReceivingAddress;
    private ImageView img;

    public static WithdrawHistoryDetailDialog newInstance(String transaction, String time, String amount, String amountArrival, String receivingAddress){
        WithdrawHistoryDetailDialog withdrawHistoryDetailDialog = new WithdrawHistoryDetailDialog();
        Bundle args = new Bundle();
        args.putString("id",transaction);
        args.putString("time",time);
        args.putString("amount",amount);
        args.putString("amountArrival",amountArrival);
        args.putString("receivingAddress",receivingAddress);
        withdrawHistoryDetailDialog.setArguments(args);
        return withdrawHistoryDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_wallet_withdraw_history,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        initAction();

        return view;
    }

    public void initView(){
        txtAmount = view.findViewById(R.id.txt_amount_dialog);
        txtAmountArrival = view.findViewById(R.id.txt_amount_arrival_dialog);
        txtTransactionId = view.findViewById(R.id.txt_transaction_id_dialog);
        txtTime = view.findViewById(R.id.txt_time_dialog);
        txtReceivingAddress = view.findViewById(R.id.txt_receiving_address_dialog);
        img = view.findViewById(R.id.img_close);
    }

    public void initAction(){
        img.setOnClickListener(view1 -> {
            dismiss();
        });

        txtTransactionId.setText(getArguments().getString("id"));
        txtTime.setText(getArguments().getString("time"));
        txtAmount.setText(getArguments().getString("amount"));
        txtAmountArrival.setText(getArguments().getString("amountArrival"));
        txtReceivingAddress.setText(getArguments().getString("receivingAddress"));
    }
}
