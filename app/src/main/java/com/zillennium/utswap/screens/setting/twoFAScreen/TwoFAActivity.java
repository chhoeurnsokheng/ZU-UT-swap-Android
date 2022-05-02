package com.zillennium.utswap.screens.setting.twoFAScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingTwofaBinding;

public class TwoFAActivity extends AppCompatActivity {

    ActivitySettingTwofaBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingTwofaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set Link Play Store
        Button btn_playstore = binding.btnPlaystore;
        btn_playstore.setOnClickListener(view -> {
            final String appPackageName = "com.google.android.apps.authenticator2"; // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        });

        // Set Click Copy Code
        LinearLayout lay_copycode = binding.layCopycode;
        lay_copycode.setOnClickListener(view -> {
            TextView txt_code = view.findViewById(R.id.txt_code);
            BasePassedActivity.onPassedCopy(this, view, txt_code.getText().toString());
        });

        //
        EditText txtedCode = binding.txtedCode;
        Button btnSubmit = binding.btnSubmit;
        ScrollView layScroll = binding.layScroll;

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(TwoFAActivity.this, ivBack);
    }

}
