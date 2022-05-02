package com.zillennium.utswap.screens.setting.signUpScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.setting.kycScreen.verificationScreen.VerificationActivity;

public class SignupActivity extends AppCompatActivity {

    private ImageView imgBack;
    private MaterialButton btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_sign_up);

        initView();

        initAction();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        btnSignUp = findViewById(R.id.btn_signup);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, VerificationActivity.class);
            startActivity(intent);
        });
    }
}
