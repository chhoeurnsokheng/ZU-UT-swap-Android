package com.zillennium.utswap.screens.setting.lockScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BaseListViewHeightActivity;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingLockScreenBinding;
import com.zillennium.utswap.models.ListLockScreen.ListLockScreen;
import com.zillennium.utswap.models.ListLockScreen.ListLockScreenAdapter;

import java.util.ArrayList;

public class LockScreenActivity extends AppCompatActivity {

    ActivitySettingLockScreenBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingLockScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set Lock Screen ListView
        String[] title_lockscreen = {
                "Lock Screen After 1 Minute",
                "Lock Screen After 2 Minute",
                "Lock Screen After 3 Minute",};


        ArrayList<ListLockScreen> lockScreenArrayList = new ArrayList<>();

        for(int i=0; i<title_lockscreen.length; i++){
            ListLockScreen lockscreen;
            if(title_lockscreen[i] == "Lock Screen After 1 Minute"){
                lockscreen = new ListLockScreen(title_lockscreen[i], true);
            }else{
                lockscreen = new ListLockScreen(title_lockscreen[i], false);
            }
            lockScreenArrayList.add(lockscreen);
        }

        ListLockScreenAdapter lockScreenAdapter = new ListLockScreenAdapter(this, lockScreenArrayList);
        ListView lvLockscreen = binding.lvLockscreen;
        lvLockscreen.setAdapter(lockScreenAdapter);
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvLockscreen);
        lvLockscreen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String title = title_lockscreen[position];

                for (int i = 0; i < adapterView.getAdapter().getCount(); i++)
                {
                    LinearLayout p_lay_item = adapterView.getChildAt(i).findViewById(R.id.lay_item);
                    ImageView p_iv_icon = adapterView.getChildAt(i).findViewById(R.id.iv_icon);
                    TextView p_txt_title = adapterView.getChildAt(i).findViewById(R.id.txt_title);

                    p_lay_item.setBackgroundResource(R.drawable.bg_lockscreen);
                    p_iv_icon.setColorFilter(adapterView.getResources().getColor(R.color.secondary));
                    p_txt_title.setTextColor(adapterView.getResources().getColor((R.color.secondary)));

                }

                LinearLayout lay_item = view.findViewById(R.id.lay_item);
                ImageView iv_icon = view.findViewById(R.id.iv_icon);
                TextView txt_title = view.findViewById(R.id.txt_title);

                lay_item.setBackgroundResource(R.drawable.bg_lockscreen_selected);
                iv_icon.setColorFilter(view.getResources().getColor(R.color.white));
                txt_title.setTextColor(view.getResources().getColor((R.color.white)));

//               switch (title){
//                   case "English":
//                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
//                       break; case "Khmer":
//                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
//                       break;
//               }




            }
        });

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(LockScreenActivity.this, ivBack);

    }

}
