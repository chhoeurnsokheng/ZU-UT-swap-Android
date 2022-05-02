package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zillennium.utswap.R;

public class EmploymentInfo extends AppCompatActivity {

    private ImageView imgBack;
    private LinearLayout btnNext;
    private EditText etOccupation, etCompany;
    private TextView txtErrorOccupation, txtErrorCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_employment_info);

        initView();

        initAction();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        btnNext = findViewById(R.id.btn_next);
        etOccupation = findViewById(R.id.et_occupation);
        etCompany = findViewById(R.id.et_company);
        txtErrorCompany = findViewById(R.id.txt_error_company);
        txtErrorOccupation = findViewById(R.id.txt_error_occupation);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnNext.setOnClickListener(view -> {
            boolean isHaveError = false;

            if(etOccupation.getText().toString().isEmpty()){
                txtErrorOccupation.setVisibility(View.VISIBLE);
                etOccupation.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(etCompany.getText().toString().isEmpty()){
                txtErrorCompany.setVisibility(View.VISIBLE);
                etCompany.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(isHaveError)
            {
                return;
            }else
            {
                Intent intent = new Intent(this, AddressInfoActivity.class);
                startActivity(intent);
            }
        });

        etOccupation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtErrorOccupation.setVisibility(View.GONE);
                etOccupation.setBackground(getDrawable(R.drawable.outline_edittext_change_color_focus));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtErrorCompany.setVisibility(View.GONE);
                etCompany.setBackground(getDrawable(R.drawable.outline_edittext_change_color_focus));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}