package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionAdapter;

import java.util.ArrayList;

public class AddressInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView imgBack;
    private Spinner spinnerCity, spinnerDistrict, spinnerCommune;
    private EditText etHouse;
    private TextView txtErrorCity, txtErrorDistrict, txtErrorCommune, txtErrorHouse;
    private LinearLayout btnNext;
    private ArrayList<Subscription> cityList;
    private ArrayList<Subscription> districtList;
    private ArrayList<Subscription> communeList;

    private String strCity,strDistrict,strCommune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_address_info);

        initView();

        initAction();

        cityList = getCityList();
        SubscriptionAdapter adapter = new SubscriptionAdapter(this,cityList);
        if(spinnerCity != null){
            spinnerCity.setAdapter(adapter);
            spinnerCity.setOnItemSelectedListener(this);
        }

        districtList = getDistrictList();
        SubscriptionAdapter adapterDistrict = new SubscriptionAdapter(this,districtList);
        if(spinnerDistrict != null){
            spinnerDistrict.setAdapter(adapterDistrict);
            spinnerDistrict.setOnItemSelectedListener(this);
        }

        communeList = getCommuneList();
        SubscriptionAdapter adapterCommune = new SubscriptionAdapter(this,communeList);
        if(spinnerCommune != null){
            spinnerCommune.setAdapter(adapterCommune);
            spinnerCommune.setOnItemSelectedListener(this);
        }
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        spinnerCity = findViewById(R.id.spinner_city);
        spinnerCommune = findViewById(R.id.spinner_commune);
        spinnerDistrict = findViewById(R.id.spinner_district);
        etHouse = findViewById(R.id.et_house);
        txtErrorCity = findViewById(R.id.txt_error_city);
        txtErrorCommune = findViewById(R.id.txt_error_commune);
        txtErrorDistrict = findViewById(R.id.txt_error_district);
        txtErrorHouse = findViewById(R.id.txt_error_house);
        btnNext = findViewById(R.id.btn_next);
    }

    private ArrayList<Subscription> getCityList() {
        cityList = new ArrayList<>();

        cityList.add(new Subscription("----- Select -----"));
        cityList.add(new Subscription("Banteay Meanchey Province"));
        cityList.add(new Subscription("Battambang Province"));
        cityList.add(new Subscription("Kompot Province"));
        cityList.add(new Subscription("Phnom Penh"));

        return cityList;
    }

    private ArrayList<Subscription> getDistrictList() {
        districtList = new ArrayList<>();

        districtList.add(new Subscription("----- Select -----"));
        districtList.add(new Subscription("Prey Chas"));
        districtList.add(new Subscription("Ta Aek"));
        districtList.add(new Subscription("Svay Sa"));
        districtList.add(new Subscription("Ta Prok"));

        return districtList;
    }

    private ArrayList<Subscription> getCommuneList() {
        communeList = new ArrayList<>();

        communeList.add(new Subscription("----- Select -----"));
        communeList.add(new Subscription("Angkor Borei"));
        communeList.add(new Subscription("Angkaol"));
        communeList.add(new Subscription("Ba Tang"));
        communeList.add(new Subscription("Chbar Ampov"));

        return communeList;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnNext.setOnClickListener(view -> {
            boolean isHaveError = false;

            //Toast.makeText(this,strCity,Toast.LENGTH_SHORT).show();

            if (spinnerCity.getChildCount() == 0)
            {
                txtErrorCity.setVisibility(View.VISIBLE);
                spinnerCity.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(etHouse.getText().toString().isEmpty())
            {
                txtErrorHouse.setVisibility(View.VISIBLE);
                etHouse.setBackground(getDrawable(R.drawable.outline_edittext_error_normal));
                isHaveError = true;
            }

            if(isHaveError) {
                return;
            }
            else {
                Intent intent =  new Intent(this, DeclarationActivity.class);
                startActivity(intent);
            }
        });

        etHouse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtErrorHouse.setVisibility(View.GONE);
                etHouse.setBackground(getDrawable(R.drawable.outline_edittext_change_color_focus));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        strCity = spinnerCity.getItemAtPosition(i).toString();
        strDistrict = spinnerDistrict.getItemAtPosition(i).toString();
        strCommune = spinnerCommune.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}