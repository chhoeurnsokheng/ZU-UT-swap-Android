package com.zillennium.utswap.screens.setting.transferScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.zillennium.utswap.R;

public class TransferBottomSheetFragment extends BottomSheetDialogFragment {

    private LinearLayout btnTransfer;

    public static TransferBottomSheetFragment newInstance(){
        TransferBottomSheetFragment transferBottomSheetFragment = new TransferBottomSheetFragment();
        transferBottomSheetFragment.setArguments(new Bundle());
        return transferBottomSheetFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_transfer_bottomsheet_fragment,container,false);

        btnTransfer = view.findViewById(R.id.btn_transfer_confirm);
        btnTransfer.setOnClickListener(view1 -> {
            dismiss();
        });

        return view;
    }
}
