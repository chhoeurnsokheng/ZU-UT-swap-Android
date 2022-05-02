package com.zillennium.utswap.screens.setting.languageScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BaseListViewHeightActivity;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySettingLanguageBinding;
import com.zillennium.utswap.models.ListLanguage.ListLanguage;
import com.zillennium.utswap.models.ListLanguage.ListLanguageAdapter;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {

    ActivitySettingLanguageBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySettingLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set Language ListView
        int[] icon_language = {R.drawable.flag_english,
                R.drawable.flag_khmer};
        String[] title_language = {"English",
                "Khmer"};


        ArrayList<ListLanguage> languageArrayList = new ArrayList<>();

        for(int i=0; i<title_language.length; i++){
            ListLanguage language;
            if(title_language[i] == "English"){
                language = new ListLanguage(title_language[i], icon_language[i], true);
            }else{
                language = new ListLanguage(title_language[i], icon_language[i], false);
            }
            languageArrayList.add(language);
        }

        ListLanguageAdapter languageAdapter = new ListLanguageAdapter(this, languageArrayList);

        ListView lvLanguage = binding.lvLanguage;
        lvLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String title = title_language[position];

                for (int i = 0; i < adapterView.getAdapter().getCount(); i++)
                {
                    ImageView icon_right_1 = adapterView.getChildAt(i).findViewById(R.id.icon_right);
                    icon_right_1.setImageResource(0);
                }

                ImageView icon_right = view.findViewById(R.id.icon_right);
                icon_right.setImageResource(R.drawable.ic_circle_check_regular);

//               switch (title){
//                   case "English":
//                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
//                       break; case "Khmer":
//                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
//                       break;
//               }




            }
        });
        lvLanguage.setAdapter(languageAdapter);
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvLanguage);



        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(LanguageActivity.this, ivBack);

    }

}
