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
import com.zillennium.utswap.databinding.FragmentKycNationalIdBinding;
import com.zillennium.utswap.screens.setting.kycScreen.CameraActivity;

public class NationalIDFragment extends Fragment {
    FragmentKycNationalIdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentKycNationalIdBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        RelativeLayout btnCameraFront = binding.btnCameraFront;
        btnCameraFront.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CameraActivity.class);
            intent.putExtra("sample", R.drawable.ic_national_id_front);
            startActivity(intent);
        });

        RelativeLayout btnCameraBack = binding.btnCameraBack;
        btnCameraBack.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CameraActivity.class);
            intent.putExtra("sample", R.drawable.ic_national_id_back);
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
