package com.zillennium.utswap.screens.setting.reserPasswordScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity;

public class NewPasswordActivity extends AppCompatActivity {

    private ImageView imgBack;
    private LinearLayout btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_new_password);

        initView();

        initAction();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        btnNext = findViewById(R.id.btn_next);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, NavbarActivity.class);
            startActivity(intent);
        });
    }
}
