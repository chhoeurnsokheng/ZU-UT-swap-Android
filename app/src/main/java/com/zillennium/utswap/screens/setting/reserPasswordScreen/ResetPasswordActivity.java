package com.zillennium.utswap.screens.setting.reserPasswordScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;


public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_reset_password);

        View btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, VerificationResetPasswordActivity.class);
            startActivity(intent);
        });

        // Set Copyright
//        viewBinding = ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(viewBinding.getRoot());
//
//        TextView txt = findViewById(R.id.txt_copyright);
//
//        String cur_year = new SimpleDateFormat("yyyy").format(new Date());
//
//        String copyright = getColoredSpanned("Copyright " + cur_year, "#FFFFFF");
//        String company = getColoredSpanned("Zillion United Co.,Ltd.","#E0AC19");
//
//        txt.setText(Html.fromHtml(copyright + " " + company));

    }

//    private String getColoredSpanned(String text, String color) {
//        String input = "<font color=" + color + ">" + text + "</font>";
//        return input;
//    }
}
