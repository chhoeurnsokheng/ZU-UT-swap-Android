package com.zillennium.utswap.screens.setting.depositScreen;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.zillennium.utswap.R;
import com.zillennium.utswap.models.PaymentMethod.CustomItem;
import com.zillennium.utswap.screens.setting.depositScreen.adapter.CustomAdapter;

import java.util.ArrayList;

public class DepositBottomSheetFragment extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener {

    private Spinner customSpinner;
    private ArrayList<CustomItem> customList;
    private LinearLayout btnSubmitDeposit;

    public static DepositBottomSheetFragment newInstance(){
        DepositBottomSheetFragment depositBottomSheetFragment = new DepositBottomSheetFragment();
        depositBottomSheetFragment.setArguments(new Bundle());
        return depositBottomSheetFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_deposit_bottomsheet_fragment,container,false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnSubmitDeposit = view.findViewById(R.id.btn_submit_deposit);

        customSpinner = view.findViewById(R.id.spinner);
        customList = getCustomList();
        CustomAdapter adapter = new CustomAdapter(this.getContext(),customList);
        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        btnSubmitDeposit.setOnClickListener(view1 -> {
            dismiss();
        });

        return view;
    }

    private ArrayList<CustomItem> getCustomList(){
        customList = new ArrayList<>();
        customList.add(new CustomItem("Choose your Payment Method",R.drawable.ic_baseline_payment_24));
        customList.add(new CustomItem("Visa/Master Card",R.drawable.visa_card_logo));
        customList.add(new CustomItem("ABA PAY",R.drawable.aba_logo));
        customList.add(new CustomItem("ACLEDA Bank",R.drawable.acleda_logo));

        return customList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        CustomItem item = (CustomItem) adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
