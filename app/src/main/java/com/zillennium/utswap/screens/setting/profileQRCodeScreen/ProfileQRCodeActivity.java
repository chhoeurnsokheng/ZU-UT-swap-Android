package com.zillennium.utswap.screens.setting.profileQRCodeScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingProfileQrcodeBinding;


public class ProfileQRCodeActivity extends AppCompatActivity {

    ActivitySettingProfileQrcodeBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingProfileQrcodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(ProfileQRCodeActivity.this, ivBack);
    }

}
