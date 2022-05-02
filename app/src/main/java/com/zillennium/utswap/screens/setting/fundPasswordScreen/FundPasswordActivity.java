package com.zillennium.utswap.screens.setting.fundPasswordScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingFundPasswordBinding;

public class FundPasswordActivity extends AppCompatActivity {

    ActivitySettingFundPasswordBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingFundPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(FundPasswordActivity.this, ivBack);
    }

}
