package com.zillennium.utswap.screens.setting.kycScreen.idTypeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivityKycIdTypeBinding;
import com.zillennium.utswap.screens.setting.kycScreen.verificationScreen.IDVerification;

public class IdTypeActivity extends AppCompatActivity {

    ActivityKycIdTypeBinding binding;
    TextView tabSelect,nationalId,passport;
    private static final int NUM_PAGES = 2;
    private ViewPager2 vpVerify;
    private FragmentStateAdapter pageAdapter;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityKycIdTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayout btnNext = binding.btnNext;
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, IDVerification.class);
            startActivity(intent);
        });

        vpVerify = binding.vpVerify;
        pageAdapter = new IdTypeActivity.ScreenSlidePageAdapter(this);
        vpVerify.setAdapter(pageAdapter);
        vpVerify.setUserInputEnabled(false);
//        vpVerify.setCurrentItem(2, false);
        vpVerify.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

        });

        tabSelect = binding.tabSelect;
        nationalId = binding.nationalId;
        passport = binding.passport;


        nationalId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeTabs(view);
                vpVerify.setCurrentItem(0, false);
            }
        });
        passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeTabs(view);
                vpVerify.setCurrentItem(1, false);
            }
        });



        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(IdTypeActivity.this, ivBack);
    }

    private void onChangeTabs(View view){
        if (view.getId() == R.id.national_id){
            tabSelect.animate().x(0).setDuration(100);
            nationalId.setTextColor(getResources().getColor(R.color.white));
            passport.setTextColor(getResources().getColor(R.color.color_main));
        } else if (view.getId() == R.id.passport){
            nationalId.setTextColor(getResources().getColor(R.color.color_main));
            passport.setTextColor(getResources().getColor(R.color.white));
            int size = passport.getWidth();
            tabSelect.animate().x(size).setDuration(100);
        }
    }

    private class ScreenSlidePageAdapter extends FragmentStateAdapter {
        public ScreenSlidePageAdapter(IdTypeActivity idTypeActivity){
            super(idTypeActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new NationalIDFragment();
                case 1:
                    return new PassportFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}
