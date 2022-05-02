package com.zillennium.utswap.screens.setting.withdrawalScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.PaymentMethod.CustomItem;
import com.zillennium.utswap.screens.setting.depositScreen.adapter.CustomAdapter;

import java.util.ArrayList;


public class WithdrawalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ImageView imgBack;
    private Spinner customSpinner;
    private ArrayList<CustomItem> customList;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_withdrawal);

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
        customList.add(new CustomItem("Choose your Receiving Address",R.drawable.ic_baseline_payment_24));
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
//        withDrawalAdapter = new WithDrawalAdapter(DataWithDrawal.LIST_OF_WITHDRAWAL_HISTORY(),onclickWithDrawHistory);
//        arrayList = DataWithDrawal.LIST_OF_WITHDRAWAL_HISTORY();
//        recyclerView.setAdapter(withDrawalAdapter);
//    }
//
//    private final WithDrawalAdapter.OnclickWithDrawHistory onclickWithDrawHistory = new WithDrawalAdapter.OnclickWithDrawHistory() {
//        @Override
//        public void onClickMe(WithDrawalHistory withDrawalHistory) {
//            WithdrawHistoryDetailDialog withdrawHistoryDetailDialog = WithdrawHistoryDetailDialog.newInstance(withDrawalHistory.getTransactionId(),withDrawalHistory.getTime(),
//                    withDrawalHistory.getAmount(),withDrawalHistory.getAmountArrival(),withDrawalHistory.getReceivingAddress());
//            withdrawHistoryDetailDialog.show(getSupportFragmentManager(),"dfa");
//        }
//    };

}
