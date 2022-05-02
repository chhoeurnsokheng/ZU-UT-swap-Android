package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zillennium.utswap.R;

public class FundPasswordActivity extends AppCompatActivity {

    private ImageView imgBack, imgShowPassword, imgShowConfirmPassword;
    private EditText editTextPassword1, editTextPassword2, editTextPassword3, editTextPassword4;
    private EditText editTextCfPassword1, editTextCfPassword2, editTextCfPassword3, editTextCfPassword4;
    private LinearLayout btnNext;
    private LinearLayout llVerify;
    private int clickCount = 2;
    private int click =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_fund_password);

        initView();

        initAction();

    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        imgShowPassword = findViewById(R.id.img_show_password);
        imgShowConfirmPassword = findViewById(R.id.img_show_confirm_password);
        llVerify = findViewById(R.id.llVerify);
        btnNext = findViewById(R.id.btn_next);
        editTextPassword1 = findViewById(R.id.edit_box_1);
        editTextPassword2 = findViewById(R.id.edit_box_2);
        editTextPassword3 = findViewById(R.id.edit_box_3);
        editTextPassword4 = findViewById(R.id.edit_box_4);

        editTextCfPassword1 = findViewById(R.id.edit_box_confirm_1);
        editTextCfPassword2 = findViewById(R.id.edit_box_confirm_2);
        editTextCfPassword3 = findViewById(R.id.edit_box_confirm_3);
        editTextCfPassword4 = findViewById(R.id.edit_box_confirm_4);

        editTextPassword1.requestFocus();
        showSoftKeyboard(editTextPassword1);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initAction(){
        imgBack.setOnClickListener(view ->{
            finish();
        });

        editTextPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextPassword2.requestFocus();
                }
                else{
                    editTextPassword1.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextPassword3.requestFocus();
                }
                else
                    editTextPassword1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextPassword3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextPassword4.requestFocus();
                }
                else
                    editTextPassword2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextPassword4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextCfPassword1.requestFocus();
                }
                else
                    editTextPassword3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextCfPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextCfPassword2.requestFocus();
                }
                else
                    editTextCfPassword1.clearFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextCfPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextCfPassword3.requestFocus();
                }
                else
                    editTextCfPassword1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextCfPassword3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextCfPassword4.requestFocus();
                }
                else
                    editTextCfPassword2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextCfPassword4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editTextCfPassword4.clearFocus();
                }
                else
                    editTextCfPassword3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgShowPassword.setOnClickListener(view -> {
            showPassword(clickCount);
            clickCount++;
        });

        imgShowConfirmPassword.setOnClickListener(view -> {
            showCfPassword(click);
            click++;
        });

        btnNext.setOnClickListener(view -> {
            /*if(editTextPassword1.getText().toString().isEmpty() && editTextPassword2.getText().toString().isEmpty() && editTextPassword3.getText().toString().isEmpty()
               && editTextPassword4.getText().toString().isEmpty())
            {
                Intent intent = new Intent(this, ContractActivity.class);
                startActivity(intent);
            }
            else
            {
               return;
            }*/
            Intent intent = new Intent(this, ContractActivity.class);
            startActivity(intent);
        });

//        btnNext.setOnClickListener(view -> {
//            boolean isEmpty =false;
//            int count = llVerify.getChildCount();
//            for (int i =0; i<count;i++)
//            {
//                EditText curText = (EditText) llVerify.getChildAt(i) ;
//                if (curText.getText().toString().isEmpty())
//                {
//                    isEmpty = true;
//                }
//            }
//
//            if(isEmpty)
//            {
//                return;
//            }
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//        });
    }

//    private void setFillAuto(ViewGroup layout)
//    {
//        int count = layout.getChildCount();
//        for (int j =0; j<count;j++)
//        {
//            EditText curText = (EditText) layout.getChildAt(j) ;
//            if(j==0)
//            {
//                curText.requestFocus();
//            }
//
//            int a = j;
//            curText.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int j, int i1, int i2) {
//                    if(!charSequence.toString().isEmpty())
//                    {
//                        if(a < count - 1)
//                        {
//                            int nextIndex = a +1;
//                            layout.getChildAt(nextIndex).requestFocus();
//                        }
//                    }
//                    /*else{
//                        if(a > 0)
//                        {
//                            int nextIndex = a -1;
//                            layout.getChildAt(nextIndex).requestFocus();
//                        }
//                        else
//                            layout.getChildAt(0).requestFocus();
//                    }*/
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//
//            curText.setOnKeyListener((view, i, keyEvent) -> {
//                if(i == KeyEvent.KEYCODE_DEL)
//                {
//                    if(a!= 0 && a<3)
//                    {
//                        layout.getChildAt(a ).clearFocus();
//                        ((EditText)layout.getChildAt(a)).setText("");
//                        int nextIndex = a - 1;
//                        ((EditText)layout.getChildAt(nextIndex)).setText("");
//                        layout.getChildAt(nextIndex ).requestFocus();
//                        return true;
//                    }
//                    else if(a==3)
//                    {
//                        layout.getChildAt(a).clearFocus();
//                        ((EditText)layout.getChildAt(a)).setText("");
//                        ((EditText)layout.getChildAt(2)).setText("");
//                        layout.getChildAt(2 ).requestFocus();
//                        return true;
//                    }
//                }
//                return false;
//            });
//
//        }
//    }

    private void showPassword(int clickCount){
        if (clickCount % 2 == 0) {
            editTextPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editTextPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editTextPassword3.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editTextPassword4.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imgShowPassword.setImageDrawable(getDrawable(R.drawable.ic_baseline_remove_red_eye_24));
        } else {
            editTextPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editTextPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editTextPassword3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editTextPassword4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imgShowPassword.setImageDrawable(getDrawable(R.drawable.ic_baseline_disabled_visible_24));
        }
    }

    private void showCfPassword(int click){
        if (click % 2 == 0) {
            editTextCfPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editTextCfPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editTextCfPassword3.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editTextCfPassword4.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imgShowConfirmPassword.setImageDrawable(getDrawable(R.drawable.ic_baseline_remove_red_eye_24));

        } else {
            editTextCfPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editTextCfPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editTextCfPassword3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editTextCfPassword4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imgShowConfirmPassword.setImageDrawable(getDrawable(R.drawable.ic_baseline_disabled_visible_24));
        }
    }

    public void showSoftKeyboard(EditText editText){
        if(editText.requestFocus()){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput((View) editText.getWindowToken(),InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}