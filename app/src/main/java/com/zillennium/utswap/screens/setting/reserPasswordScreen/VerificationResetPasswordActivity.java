package com.zillennium.utswap.screens.setting.reserPasswordScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;

public class VerificationResetPasswordActivity extends AppCompatActivity {

    private ImageView imgBack, imgCorrect;
    private EditText editText1, editText2, editText3, editText4, editText5, editText6;
    private LinearLayout linearCountDown;
    private TextView countdownText;
    private LinearLayout btnNext;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 12000; //120 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_verification_reset_password);

        initView();

        initAction();

    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        editText1 = findViewById(R.id.edit_box_1);
        editText2 = findViewById(R.id.edit_box_2);
        editText3 = findViewById(R.id.edit_box_3);
        editText4 = findViewById(R.id.edit_box_4);
        editText5 = findViewById(R.id.edit_box_5);
        editText6 = findViewById(R.id.edit_box_6);
        imgCorrect = findViewById(R.id.img_correct);
        linearCountDown = findViewById(R.id.linear_countdown);
        countdownText = findViewById(R.id.txt_count_down);
        btnNext = findViewById(R.id.btn_next);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> { finish();});

        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewPasswordActivity.class);
            startActivity(intent);
        });

        setupOTPInputs();
    }

    private void setupOTPInputs(){
        startTimer();
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editText2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editText3.requestFocus();
                }
                else
                    editText1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editText4.requestFocus();
                }
                else
                    editText2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editText5.requestFocus();
                }
                else
                    editText3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    editText6.requestFocus();
                }
                else
                    editText4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    imgCorrect.setVisibility(View.VISIBLE);
                    linearCountDown.setVisibility(View.GONE);
                    editText1.setBackground(getResources().getDrawable(R.drawable.bg_border_green_correct));
                    editText2.setBackground(getResources().getDrawable(R.drawable.bg_border_green_correct));
                    editText3.setBackground(getResources().getDrawable(R.drawable.bg_border_green_correct));
                    editText4.setBackground(getResources().getDrawable(R.drawable.bg_border_green_correct));
                    editText5.setBackground(getResources().getDrawable(R.drawable.bg_border_green_correct));
                    editText6.setBackground(getResources().getDrawable(R.drawable.bg_border_green_correct));
                    stopTimer();
                }
                else{
                    imgCorrect.setVisibility(View.GONE);
                    linearCountDown.setVisibility(View.VISIBLE);
                    editText5.requestFocus();
                    editText1.setBackground(getResources().getDrawable(R.drawable.bg_corner));
                    editText2.setBackground(getResources().getDrawable(R.drawable.bg_corner));
                    editText3.setBackground(getResources().getDrawable(R.drawable.bg_corner));
                    editText4.setBackground(getResources().getDrawable(R.drawable.bg_corner));
                    editText5.setBackground(getResources().getDrawable(R.drawable.bg_corner));
                    editText6.setBackground(getResources().getDrawable(R.drawable.bg_corner));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long l) {
                countdownText.setText(""+l/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void stopTimer(){
        countDownTimer.cancel();
    }
}