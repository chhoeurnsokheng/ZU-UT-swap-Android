package com.zillennium.utswap.screens.setting.depositScreen.dialog;

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

public class DepositHistoryDetailDialog extends DialogFragment {

    private View view;
    private ImageView imgClose, imgLogo;
    private TextView txtDepositMethod, txtTransactionId, txtAmount, txtFee, txtNetValue, txtRechargeTime, txtStatus;

    public static DepositHistoryDetailDialog newInstance(int img, String depositMethod, String transactionId, String amount, String fee, String netValue, String rechargeTime, String status){
        DepositHistoryDetailDialog depositHistoryDetailDialog = new DepositHistoryDetailDialog();
        Bundle args = new Bundle();
        args.putInt("logo",img);
        args.putString("method",depositMethod);
        args.putString("transaction",transactionId);
        args.putString("amount",amount);
        args.putString("fee",fee);
        args.putString("netValue",netValue);
        args.putString("recharge",rechargeTime);
        args.putString("status",status);
        depositHistoryDetailDialog.setArguments(args);
        return depositHistoryDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_wallet_deposit_history,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        initAction();

        return view;
    }

    public void initView(){
        imgClose = view.findViewById(R.id.img_close);
        imgLogo = view.findViewById(R.id.img_logo_cash);
        txtDepositMethod = view.findViewById(R.id.txt_deposit_method);
        txtTransactionId = view.findViewById(R.id.txt_transaction_id_dialog);
        txtAmount = view.findViewById(R.id.txt_amount_dialog);
        txtFee = view.findViewById(R.id.txt_fee_dialog);
        txtNetValue = view.findViewById(R.id.txt_net_value_dialog);
        txtRechargeTime = view.findViewById(R.id.txt_recharge_time_dialog);
        txtStatus = view.findViewById(R.id.txt_status_dialog);
    }

    public void initAction(){
        imgClose.setOnClickListener(view1 -> {
            dismiss();
        });

        //txtDepositMethod.setText(getArguments().getString("method"));
        txtTransactionId.setText(getArguments().getString("transaction"));
        txtAmount.setText(getArguments().getString("amount"));
        txtFee.setText(getArguments().getString("fee"));
        txtNetValue.setText(getArguments().getString("netValue"));
        txtRechargeTime.setText(getArguments().getString("recharge"));
        txtStatus.setText(getArguments().getString("status"));

    }
}
