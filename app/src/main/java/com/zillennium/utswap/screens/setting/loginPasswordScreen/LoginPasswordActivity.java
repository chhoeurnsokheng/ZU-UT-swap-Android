package com.zillennium.utswap.screens.setting.loginPasswordScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingLoginPasswordBinding;

public class LoginPasswordActivity extends AppCompatActivity {

    ActivitySettingLoginPasswordBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingLoginPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(LoginPasswordActivity.this, ivBack);
    }

}
