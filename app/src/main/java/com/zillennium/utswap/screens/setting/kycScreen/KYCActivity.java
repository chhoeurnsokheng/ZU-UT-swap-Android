package com.zillennium.utswap.screens.setting.kycScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingKycBinding;

public class KYCActivity extends AppCompatActivity {

    ActivitySettingKycBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingKycBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(KYCActivity.this, ivBack);
    }

}
