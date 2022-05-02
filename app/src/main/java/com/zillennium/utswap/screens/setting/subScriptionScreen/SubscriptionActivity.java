package com.zillennium.utswap.screens.setting.subScriptionScreen;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivityWalletSubscriptionBinding;
import com.zillennium.utswap.datas.subscriptionData.DataSubscription;
import com.zillennium.utswap.models.Subscription.Subscription;
import com.zillennium.utswap.models.SubscriptionHistory.SubscriptionHistory;
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionAdapter;
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionHistoryAdapter;
import com.zillennium.utswap.screens.setting.subScriptionScreen.dialog.SubscriptionDetailDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SubscriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ActivityWalletSubscriptionBinding binding;
    private Spinner customSpinner;
    private ArrayList<Subscription> customList;
    Calendar calendar = Calendar.getInstance();
    EditText edtxtFrom, edtxtTo;
    RecyclerView recyclerView;
    private SubscriptionHistoryAdapter subscriptionHistoryAdapter;
    private ArrayList<SubscriptionHistory> arrayList = new ArrayList<>();

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityWalletSubscriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        edtxtFrom = binding.edtxtFrom;
        edtxtTo = binding.edtxtTo;

        initView();

        initAction();

        setAdapter();

        customList = getCustomList();
        SubscriptionAdapter adapter = new SubscriptionAdapter(this,customList);
        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(SubscriptionActivity.this, ivBack);
    }

    public void initView(){
        customSpinner = findViewById(R.id.spinner_subscription);
        recyclerView = findViewById(R.id.list_subscription_history);
    }

    public void initAction(){
        DatePickerDialog.OnDateSetListener date_start = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONDAY, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String Format = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format, Locale.US);

                edtxtFrom.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        DatePickerDialog.OnDateSetListener date_end = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONDAY, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String Format = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format, Locale.US);

                edtxtTo.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        edtxtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SubscriptionActivity.this, date_start, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtxtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SubscriptionActivity.this, date_end, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private ArrayList<Subscription> getCustomList() {
        customList = new ArrayList<>();

        customList.add(new Subscription("UT New Airport 38Ha..."));
        customList.add(new Subscription("UT Veng Sreng 2719..."));
        customList.add(new Subscription("UT KT 1665..."));
        customList.add(new Subscription("UT Muk Kampul 16644..."));
        customList.add(new Subscription("UT Siem Reap 17140..."));

        return customList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Subscription item = (Subscription) adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setAdapter(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        subscriptionHistoryAdapter = new SubscriptionHistoryAdapter(DataSubscription.LIST_OF_SUBSCRIPTION_HISTORY(),onclickSubscriptionHistory);
        arrayList = DataSubscription.LIST_OF_SUBSCRIPTION_HISTORY();
        recyclerView.setAdapter(subscriptionHistoryAdapter);
    }

    private final SubscriptionHistoryAdapter.OnclickSubscriptionHistory onclickSubscriptionHistory = new SubscriptionHistoryAdapter.OnclickSubscriptionHistory() {
        @Override
        public void onClickMe(SubscriptionHistory subscriptionHistory) {
            SubscriptionDetailDialog subscriptionDetailDialog = SubscriptionDetailDialog.newInstance(
                    subscriptionHistory.getNameSub(),subscriptionHistory.getValue(), subscriptionHistory.getDate(), subscriptionHistory.getPrice(),
                    subscriptionHistory.getVolume(), subscriptionHistory.getTransactionId()
            );

            subscriptionDetailDialog.show(getSupportFragmentManager(),"adfafa");
        }
    };
}
