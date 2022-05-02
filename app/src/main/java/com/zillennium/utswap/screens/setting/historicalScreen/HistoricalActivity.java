package com.zillennium.utswap.screens.setting.historicalScreen;

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
import com.zillennium.utswap.databinding.ActivityWalletHistoricalBinding;
import com.zillennium.utswap.datas.historicalData.DataHistorical;
import com.zillennium.utswap.models.Historical.Historical;
import com.zillennium.utswap.models.Subscription.Subscription;
import com.zillennium.utswap.screens.setting.historicalScreen.adapter.HistoricalAdapter;
import com.zillennium.utswap.screens.setting.historicalScreen.adapter.HistoricalFilterAdapter;
import com.zillennium.utswap.screens.setting.historicalScreen.dialog.HistoricalDetailDialog;
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HistoricalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ActivityWalletHistoricalBinding binding;
    private Spinner customSpinner,spinnerFilter;
    private ArrayList<Subscription> customList;
    private ArrayList<Subscription> filterList;
    Calendar calendar = Calendar.getInstance();
    EditText edtxtFrom, edtxtTo;
    RecyclerView recyclerView;
    private HistoricalAdapter historicalAdapter;
    private ArrayList<Historical> arrayList = new ArrayList<>();

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityWalletHistoricalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        edtxtFrom = binding.edtxtFrom;
        edtxtTo = binding.edtxtTo;

        initView();

        initAction();

        setAdapter();

        filterList = getFilterList();
        HistoricalFilterAdapter filterAdapter = new HistoricalFilterAdapter(this,filterList);
        if(spinnerFilter != null){
            spinnerFilter.setAdapter(filterAdapter);
            spinnerFilter.setOnItemSelectedListener(this);
        }

        customList = getCustomList();
        SubscriptionAdapter adapter = new SubscriptionAdapter(this,customList);
        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }
        
        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(HistoricalActivity.this, ivBack);
    }

    public void initView(){
        customSpinner = findViewById(R.id.spinner_historical);
        spinnerFilter = findViewById(R.id.spinner_filter);
        recyclerView = findViewById(R.id.list_historical_history);
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
                new DatePickerDialog(HistoricalActivity.this, date_start, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtxtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HistoricalActivity.this, date_end, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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

    private ArrayList<Subscription> getFilterList() {
        filterList = new ArrayList<>();

        filterList.add(new Subscription("-ALL-"));
        filterList.add(new Subscription("Sold"));
        filterList.add(new Subscription("Bought"));
        filterList.add(new Subscription("Subscribe ITO"));
        filterList.add(new Subscription("Sell ITO"));
        filterList.add(new Subscription("Buy Back"));

        return filterList;
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
        historicalAdapter = new HistoricalAdapter(DataHistorical.LIST_OF_HISTORICAL_HISTORY(),onClickHistoricalHistory);
        arrayList = DataHistorical.LIST_OF_HISTORICAL_HISTORY();
        recyclerView.setAdapter(historicalAdapter);
    }

    private final HistoricalAdapter.OnClickHistoricalHistory onClickHistoricalHistory =  new HistoricalAdapter.OnClickHistoricalHistory() {
        @Override
        public void clickMe(Historical historical) {
            HistoricalDetailDialog historicalDetailDialog = HistoricalDetailDialog.newInstance(historical.getNameSub(), historical.getDate(), historical.getValue(), historical.getVolume(),historical.getPrice(), historical.getSettlement(), historical.getUtBalance());
            historicalDetailDialog.show(getSupportFragmentManager(),"faag");
        }
    };
}
