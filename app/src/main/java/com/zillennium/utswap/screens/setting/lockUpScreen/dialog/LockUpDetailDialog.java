package com.zillennium.utswap.screens.setting.lockUpScreen.dialog;

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


public class LockUpDetailDialog extends DialogFragment {

    private View view;
    private TextView txtAmount, txtDate, txtProject, txtPeriod, txtMaturity;
    private ImageView img;

    public static LockUpDetailDialog newInstance(String amount, String date, String project, String period, String maturity){
        LockUpDetailDialog lockUpDetailDialog = new LockUpDetailDialog();
        Bundle args = new Bundle();
        args.putString("amount",amount);
        args.putString("date",date);
        args.putString("project",project);
        args.putString("period",period);
        args.putString("maturity",maturity);
        lockUpDetailDialog.setArguments(args);
        return lockUpDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_wallet_lock_up_history,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        initAction();

        return view;
    }

    public void initView(){
        img = view.findViewById(R.id.img_close);
        txtAmount = view.findViewById(R.id.txt_amount_dialog);
        txtDate = view.findViewById(R.id.txt_date_dialog);
        txtMaturity = view.findViewById(R.id.txt_maturity_date_dialog);
        txtProject = view.findViewById(R.id.txt_project_dialog);
        txtPeriod = view.findViewById(R.id.txt_lock_period_dialog);
    }

    public void initAction(){
        img.setOnClickListener(view1 -> {
            dismiss();
        });

        txtAmount.setText(getArguments().getString("amount"));
        txtDate.setText(getArguments().getString("date"));
        txtMaturity.setText(getArguments().getString("maturity"));
        txtProject.setText(getArguments().getString("project"));
        txtPeriod.setText(getArguments().getString("period"));
    }
}
