package com.zillennium.utswap.screens.setting.kycScreen.selfieHoldingScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivityKycSelfieHoldingBinding;
import com.zillennium.utswap.screens.setting.kycScreen.SelfieActivity;
import com.zillennium.utswap.screens.setting.kycScreen.verificationScreen.EmploymentInfo;

public class SelfieHoldingActivity extends AppCompatActivity {

    ActivityKycSelfieHoldingBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityKycSelfieHoldingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        LinearLayout btnCameraSelfie = binding.btnCameraSelfie;
        btnCameraSelfie.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SelfieActivity.class);
            startActivity(intent);
        });

        LinearLayout btnNext = binding.btnNext;
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, EmploymentInfo.class);
            startActivity(intent);
        });

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(SelfieHoldingActivity.this, ivBack);
    }

}
