package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.Subscription.Subscription;
import com.zillennium.utswap.screens.setting.kycScreen.selfieHoldingScreen.SelfieHoldingActivity;
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class IDVerification extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView imgBack, imgCalendar;
    private EditText etFirstName, etLastName, etDate;
    private TextView txtErrorFirsName, txtErrorLastName, txtErrorDate;
    private LinearLayout btnNext;
    private Spinner customSpinner;
    private ArrayList<Subscription> customList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_id_verification);

        initView();

        initAction();

        customList = getCustomList();
        SubscriptionAdapter adapter = new SubscriptionAdapter(this,customList);
        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etDate = findViewById(R.id.et_date);
        txtErrorDate = findViewById(R.id.txt_error_date);
        txtErrorFirsName = findViewById(R.id.txt_error_first_name);
        txtErrorLastName = findViewById(R.id.txt_error_last_name);
        imgCalendar = findViewById(R.id.img_calendar);
        btnNext = findViewById(R.id.btn_next);
        customSpinner = findViewById(R.id.spinner);
    }

    private ArrayList<Subscription> getCustomList() {
        customList = new ArrayList<>();

        customList.add(new Subscription("Male"));
        customList.add(new Subscription("Female"));

        return customList;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONDAY, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            String Format = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format, Locale.US);

            etDate.setText(simpleDateFormat.format(calendar.getTime()));

        };

        imgCalendar.setOnClickListener(view -> new DatePickerDialog(IDVerification.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        btnNext.setOnClickListener(view -> {
            boolean isHaveError = false;

            if(etFirstName.getText().toString().isEmpty()){
                txtErrorFirsName.setVisibility(View.VISIBLE);
                etFirstName.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(etLastName.getText().toString().isEmpty()){
                txtErrorLastName.setVisibility(View.VISIBLE);
                etLastName.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(etDate.getText().toString().isEmpty()){
                txtErrorDate.setVisibility(View.VISIBLE);
                etDate.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(isHaveError)
            {
                return;
            }else
            {
                Intent intent = new Intent(this, SelfieHoldingActivity.class);
                startActivity(intent);
            }
        });

        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtErrorFirsName.setVisibility(View.GONE);
                etFirstName.setBackground(getDrawable(R.drawable.outline_edittext_change_color_focus));
            }
        });

        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtErrorLastName.setVisibility(View.GONE);
                etLastName.setBackground(getDrawable(R.drawable.outline_edittext_change_color_focus));
            }
        });

        etDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtErrorDate.setVisibility(View.GONE);
                etDate.setBackground(getDrawable(R.drawable.outline_edittext_change_color_focus));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Subscription item = (Subscription) adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}