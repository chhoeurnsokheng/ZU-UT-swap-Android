package com.zillennium.utswap.screens.setting.kycScreen.idTypeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.zillennium.utswap.R;
import com.zillennium.utswap.databinding.FragmentKycPassportBinding;
import com.zillennium.utswap.screens.setting.kycScreen.CameraActivity;

public class PassportFragment extends Fragment {
    FragmentKycPassportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentKycPassportBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        RelativeLayout btnPassport = binding.btnPassport;
        btnPassport.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CameraActivity.class);
            intent.putExtra("sample", R.drawable.ic_passport_front);
            startActivity(intent);
        });
        

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.getRoot().requestLayout();
    }



}
