package com.zillennium.utswap.screens.setting.transferScreen.dialog;

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


public class TransferHistoryDetailDialog extends DialogFragment {

    private View view;
    private TextView txtTransaction, txtTime, txtSender, txtReceiver, txtAmount, txtTotal;
    private ImageView img;

    public static TransferHistoryDetailDialog newInstance(String transaction, String time, String sender, String receiver, String amount, String total){
        TransferHistoryDetailDialog transferHistoryDetailDialog = new TransferHistoryDetailDialog();
        Bundle args = new Bundle();
        args.putString("id",transaction);
        args.putString("time",time);
        args.putString("sender",sender);
        args.putString("receiver",receiver);
        args.putString("amount",amount);
        args.putString("total",total);
        transferHistoryDetailDialog.setArguments(args);
        return transferHistoryDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_wallet_transfer_history,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        initAction();

        return view;
    }

    public void initView(){
        txtTransaction = view.findViewById(R.id.txt_transaction_id_dialog);
        txtAmount = view.findViewById(R.id.txt_amount_dialog);
        txtReceiver = view.findViewById(R.id.txt_receiver_dialog);
        txtSender = view.findViewById(R.id.txt_sender_dialog);
        txtTotal = view.findViewById(R.id.txt_total_dialog);
        txtTime = view.findViewById(R.id.txt_time_dialog);
        img = view.findViewById(R.id.img_close);
    }

    public void initAction(){
        txtTransaction.setText(getArguments().getString("id"));
        txtAmount.setText(getArguments().getString("amount"));
        txtReceiver.setText(getArguments().getString("receiver"));
        txtSender.setText(getArguments().getString("sender"));
        txtTotal.setText(getArguments().getString("total"));
        txtTime.setText(getArguments().getString("time"));

        img.setOnClickListener(v -> {
            dismiss();
        });
    }
}
