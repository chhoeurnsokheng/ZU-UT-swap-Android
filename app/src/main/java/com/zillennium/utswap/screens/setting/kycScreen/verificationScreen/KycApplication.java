package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity;

public class KycApplication extends AppCompatActivity {

    private ImageView imgBack;
    private MaterialButton btnLetStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_application);

        initView();

        initAction();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        btnLetStart = findViewById(R.id.btn_accept);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {finish();});

        btnLetStart.setOnClickListener(view -> {
            Intent intent = new Intent(this, NavbarActivity.class);
            startActivity(intent);
        });
    }
}