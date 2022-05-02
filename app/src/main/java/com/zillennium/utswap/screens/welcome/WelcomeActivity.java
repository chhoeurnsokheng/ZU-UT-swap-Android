package com.zillennium.utswap.screens.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.utswapapp.screens.welcome.adpater.MyAdapter;
import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.setting.signInScreen.SigninActivity;
import com.zillennium.utswap.screens.setting.signUpScreen.SignupActivity;

public class WelcomeActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private MaterialButton btnSignIn, btnRegister;
    private TextView[] dostTv;
    int[] layouts;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        iniView();

        initAction();
    }

    public void iniView(){
        viewPager = findViewById(R.id.welcome_slider);
        linearLayout = findViewById(R.id.dotsLayout);
        btnRegister = findViewById(R.id.btn_register);
        btnSignIn = findViewById(R.id.btn_signIn);
    }

    public void initAction(){
        layouts = new int[] { R.layout.fragment_welcome_slide_1, R.layout.fragment_welcome_slide_2 , R.layout.fragment_welcome_slide_3,R.layout.fragment_welcome_slide_4};
        myAdapter = new MyAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(myAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDots(0);

        btnSignIn.setOnClickListener(view -> {
            Intent intent =  new Intent(this, SigninActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent =  new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }

    public void setDots(int page){
        linearLayout.removeAllViews();
        dostTv = new TextView[layouts.length];
        for (int i=0;i< dostTv.length;i++)
        {
            dostTv[i] = new TextView(this);
            dostTv[i].setText(Html.fromHtml("&#8226;"));
            dostTv[i].setTextSize(30);
            dostTv[i].setTextColor(Color.parseColor("#a9b4bb"));
            linearLayout.addView(dostTv[i]);
        }

        if(dostTv.length > 0)
        {
            dostTv[page].setTextColor(Color.parseColor("#FFFF00"));
        }
    }
}
