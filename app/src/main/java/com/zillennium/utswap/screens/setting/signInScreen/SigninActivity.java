package com.zillennium.utswap.screens.setting.signInScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity;
import com.zillennium.utswap.screens.setting.reserPasswordScreen.ResetPasswordActivity;

public class SigninActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private TextView btnForgot;
    private ImageView imgBack, imgCapCha;
    private MaterialButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_sign_in);

        initView();

        initAction();

    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        btnSignIn = findViewById(R.id.btn_signIn);
        imgCapCha = findViewById(R.id.img_numeric_capcha);
        btnForgot = findViewById(R.id.btn_forgot);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(SigninActivity.this, NavbarActivity.class);
            startActivity(intent);
        });

        btnForgot.setOnClickListener(view -> {
            Intent intent = new Intent(SigninActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });

//        Glide.with(imgCapCha.getContext())
//                .load("https://utswap.io/Verify/code")
//                .into(imgCapCha);
//
//        imgCapCha.setOnClickListener(view -> {
//            Glide.with(imgCapCha.getContext())
//                    .load("https://utswap.io/Verify/code")
//                    .into(imgCapCha);
//        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

}