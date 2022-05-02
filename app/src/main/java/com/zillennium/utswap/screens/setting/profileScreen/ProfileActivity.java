package com.zillennium.utswap.screens.setting.profileScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.bases.BaseListViewHeightActivity;
import com.zillennium.utswap.databinding.ActivitySettingProfileBinding;
import com.zillennium.utswap.models.ListProfile.ListProfile;
import com.zillennium.utswap.models.ListProfile.ListProfileAdapter;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    ActivitySettingProfileBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set Personal ListView
        String[] title_personal = {"Full Name",
                "Email",
                "Phone Number"};
        String[] desc_personal = {"Bunna Panhchakrith",
                "b.panhchakrith@gmail.com",
                "0966897978"};

        ArrayList<ListProfile> personalArrayList = new ArrayList();

        for(int i=0; i<title_personal.length; i++){
            ListProfile personal = new ListProfile(title_personal[i], desc_personal[i]);
            personalArrayList.add(personal);
        }

        ListProfileAdapter profileAdapter = new ListProfileAdapter(ProfileActivity.this, personalArrayList);

        ListView lvPersonal = binding.lvPersonal;
        lvPersonal.setAdapter(profileAdapter);
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvPersonal);

        // Set Career ListView
        String[] title_career = {"Company",
                "Occupation"};
        String[] desc_career = {"Z Valley",
                "Web Developer"};

        ArrayList<ListProfile> careerArrayList = new ArrayList<>();

        for(int i=0; i<title_career.length; i++){
            ListProfile career = new ListProfile(title_career[i], desc_career[i]);
            careerArrayList.add(career);
        }

        ListProfileAdapter careerAdapter = new ListProfileAdapter(ProfileActivity.this, careerArrayList);

        ListView lvCareer = binding.lvCareer;
        lvCareer.setAdapter(careerAdapter);
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvCareer);

        // Set Bank ListView
        String[] title_bank = {"Account Name",
                "Account Number",
                "Bank Name",
                "Bank City",
                "Bank Address"};
        String[] desc_bank = {"Bunna Panhchakrith",
                "123456789",
                "ABA Bank",
                "Phnom Penh",
                "Phnom Penh, Cambodia, 12000"};

        ArrayList<ListProfile> bankArrayList = new ArrayList<>();

        for(int i=0; i<title_bank.length; i++){
            ListProfile bank = new ListProfile(title_bank[i], desc_bank[i]);
            bankArrayList.add(bank);
        }

        ListProfileAdapter bankAdapter = new ListProfileAdapter(ProfileActivity.this, bankArrayList);

        ListView lvBank = binding.lvBank;
        lvBank.setAdapter(bankAdapter);
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvBank);

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        ivBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });
    }

}
