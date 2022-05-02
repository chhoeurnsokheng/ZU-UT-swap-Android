package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;

public class DeclarationActivity extends AppCompatActivity {

    private ImageView imgBack;
    private TextView txtContent;
    private MaterialButton btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_declaration);

        initView();

        initAction();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        txtContent = findViewById(R.id.txt_content);
        btnAccept = findViewById(R.id.btn_accept);
    }

    public void initAction(){
        imgBack.setOnClickListener(view ->{
            finish();
        });

        btnAccept.setOnClickListener(view -> {
            Intent intent = new Intent(this, FundPasswordActivity.class);
            startActivity(intent);
        });

        txtContent.setText("1- I acknowledge and confirm that the information provided above is true and correct to the best of my knowledge and belief. In case any of the above specified information is found to be false or untrue or misleading or misrepresenting, I am aware that I may liable for it. \n" +
                "\n" +
                "2- I hereby authorize you “Zillion United” to disclose, share, remit in any form, mode or manner, all / any of the information provided by me through UT Swap platform, including all changes, updates to such information as and when provided by me to Trustee, judicial authorities, tax authorities agencies for transaction/operation involve to me or wherever it is legally required and other investigation agencies without any obligation of advising me of the same.  \n" +
                "\n" +
                "3- I also undertake to keep you informed in writing about any changes/ modification to the above information in future and also undertake to provide any other additional information as may be required by you and/or any other regulations/authority. \n" +
                "\n" +
                "4- I acknowledge and understand I shall join Zillion United training on UT Swap platform to understand the platform and its rule to get my account become officially trading account.");

    }
}