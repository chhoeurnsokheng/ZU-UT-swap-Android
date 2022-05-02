package com.zillennium.utswap.screens.setting.depositScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.databinding.ActivityWalletDepositBinding;
import com.zillennium.utswap.models.DepositHistory.DepositHistory;
import com.zillennium.utswap.models.PaymentMethod.CustomItem;
import com.zillennium.utswap.screens.setting.depositScreen.adapter.CustomAdapter;

import java.util.ArrayList;

public class DepositActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ActivityWalletDepositBinding binding;
    private ArrayList<DepositHistory> arrayList = new ArrayList<>();
    private ImageView imgBack;
    private Spinner customSpinner;
    private ArrayList<CustomItem> customList;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityWalletDepositBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        customList = getCustomList();
        CustomAdapter adapter = new CustomAdapter(this,customList);
        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        initAction();

    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        customSpinner = findViewById(R.id.spinner);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {finish();});
    }

    private ArrayList<CustomItem> getCustomList(){
        customList = new ArrayList<>();
        customList.add(new CustomItem("Choose your Payment Method",R.drawable.ic_baseline_payment_24));
        customList.add(new CustomItem("Visa/Master Card",R.drawable.visa_card_logo));
        customList.add(new CustomItem("ABA PAY",R.drawable.aba_logo));
        customList.add(new CustomItem("ACLEDA Bank",R.drawable.acleda_logo));

        return customList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        CustomItem item = (CustomItem) adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    public void setAdapter(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        depositAdapter = new DepositAdapter(DataDepositHistory.LIST_OF_DEPOSIT_HISTORY(),onclickDepositHistory);
//        arrayList = DataDepositHistory.LIST_OF_DEPOSIT_HISTORY();
//        recyclerView.setAdapter(depositAdapter);
//    }

//    private final DepositAdapter.OnclickDepositHistory onclickDepositHistory = new DepositAdapter.OnclickDepositHistory() {
//        @Override
//        public void onClickMe(DepositHistory depositHistory) {
//            DepositHistoryDetailDialog depositHistoryDetailDialog = DepositHistoryDetailDialog.newInstance(depositHistory.getImagePath(),depositHistory.getDepositMethod()
//                    ,depositHistory.getTransactionId(),depositHistory.getAmount(),depositHistory.getFee()
//                    ,depositHistory.getNetValue(),depositHistory.getRechargeTime(),depositHistory.getStatus());
//            depositHistoryDetailDialog.show(getSupportFragmentManager(),"dfagag");
//        }
//    };

}
