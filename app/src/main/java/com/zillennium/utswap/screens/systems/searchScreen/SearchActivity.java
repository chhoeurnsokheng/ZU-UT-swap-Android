package com.zillennium.utswap.screens.systems.searchScreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BaseListViewHeightActivity;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySystemSearchBinding;
import com.zillennium.utswap.models.ListMenu.ListMenu;
import com.zillennium.utswap.models.ListMenu.ListMenuAdapter;
import com.zillennium.utswap.screens.setting.creditCardScreen.CreditCardActivity;
import com.zillennium.utswap.screens.setting.kycScreen.KYCActivity;
import com.zillennium.utswap.screens.setting.languageScreen.LanguageActivity;
import com.zillennium.utswap.screens.setting.lockScreen.LockScreenActivity;
import com.zillennium.utswap.screens.setting.profileScreen.ProfileActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ActivitySystemSearchBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySystemSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText edSearch = binding.edSearch;


        String[] title = {"Trading", "History", "Project", "Title Deed", "Land Size", "Total UT", "Indiaction Price", "Future Price", "Address", "Google Map"};

        ArrayList<Search> searchArrayList = new ArrayList<>();

        for(int i=0; i<title.length; i++){
            Search project = new Search(0, title[i]);
            searchArrayList.add(project);
        }



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SearchAdapter(searchArrayList, onclickItemSearch));



        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                LinearLayout layResult = binding.layResult;
                LinearLayout layDefault = binding.layDefault;
                ImageView btnClear = binding.btnClear;

                if(charSequence.length() > 1){
                    layDefault.setVisibility(View.GONE);
                    layResult.setVisibility(View.VISIBLE);
                    btnClear.setVisibility(View.VISIBLE);
                    btnClear.setOnClickListener(view -> {
                        edSearch.setText("");
                    });
                }else{
                    layDefault.setVisibility(View.VISIBLE);
                    layResult.setVisibility(View.GONE);
                    btnClear.setVisibility(View.GONE);
                    btnClear.setOnClickListener(view -> {
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        recyclerView.setOnClickListener(view -> {
            edSearch.setText("UT Swap Test Search");
            Toast.makeText(this, "hello world", Toast.LENGTH_LONG).show();
        });
        // Set Setting ListView
        int[] icon_setting = {R.drawable.ic_user_pen_regular,
                R.drawable.ic_rectangle_list_regular,
                R.drawable.ic_credit_card_front_regular,
                R.drawable.ic_language_regular,
                R.drawable.ic_timer_regular};
        String[] title_setting = {"Profile",
                "KYC",
                "Credit Card",
                "Language",
                "Lock Screen"};

        ArrayList<ListMenu> settingArrayList = new ArrayList<>();

        for(int i=0; i<title_setting.length; i++){
            ListMenu setting = new ListMenu(title_setting[i], icon_setting[i]);
            settingArrayList.add(setting);
        }

        ListMenuAdapter accountAdapter = new ListMenuAdapter(this, settingArrayList);

        ListView lvSettings = binding.lvSettings;
        lvSettings.setAdapter(accountAdapter);
        lvSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent;

                String title = title_setting[position];

                switch (title){
                    case "Profile":
                        intent = new Intent(view.getContext(), ProfileActivity.class);
                        break;
                    case "Credit Card":
                        intent = new Intent(view.getContext(), CreditCardActivity.class);
                        break;
                    case "KYC":
                        intent = new Intent(view.getContext(), KYCActivity.class);
                        break;
                    case "Language":
                        intent = new Intent(view.getContext(), LanguageActivity.class);
                        break;
                    case "Lock Screen":
                        intent = new Intent(view.getContext(), LockScreenActivity.class);
                        break;
                    default: intent = new Intent(view.getContext(), ProfileActivity.class); // As Profile
                }

                startActivity(intent);
            }
        });
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvSettings);
        
        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(SearchActivity.this, ivBack);
    }

    private final SearchAdapter.OnclickItemSearch onclickItemSearch = textSearch -> {
        EditText edSearch = binding.edSearch;
        edSearch.setText(textSearch);
        edSearch.setSelection(edSearch.getText().length());
    };
}
