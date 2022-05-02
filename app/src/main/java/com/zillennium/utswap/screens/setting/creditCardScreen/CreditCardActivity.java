package com.zillennium.utswap.screens.setting.creditCardScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingCreditCardBinding;
import com.zillennium.utswap.screens.setting.addCardScreen.AddCardActivity;

public class CreditCardActivity extends AppCompatActivity {

    ActivitySettingCreditCardBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingCreditCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        com.vinaygaba.creditcardview.CreditCardView card1 = binding.card1;


        RelativeLayout btnAddCard = binding.btnAddCard;
        btnAddCard.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddCardActivity.class);
            startActivity(intent);
        });

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(CreditCardActivity.this, ivBack);
    }


}
