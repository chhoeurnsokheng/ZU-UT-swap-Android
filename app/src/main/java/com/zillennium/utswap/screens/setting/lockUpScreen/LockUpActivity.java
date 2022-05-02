package com.zillennium.utswap.screens.setting.lockUpScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivityWalletLockUpBinding;
import com.zillennium.utswap.datas.lockUpData.DataLockUp;
import com.zillennium.utswap.models.LockUpHistory.LockUpHistory;
import com.zillennium.utswap.models.Subscription.Subscription;
import com.zillennium.utswap.screens.setting.lockUpScreen.adapter.LockUpAdapter;
import com.zillennium.utswap.screens.setting.lockUpScreen.dialog.LockUpDetailDialog;
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionAdapter;

import java.util.ArrayList;

public class LockUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ActivityWalletLockUpBinding binding;
    private Spinner customSpinner;
    private ArrayList<Subscription> customList;
    RecyclerView recyclerView;
    private LockUpAdapter lockUpAdapter;
    private ArrayList<LockUpHistory> arrayList = new ArrayList<>();

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityWalletLockUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        BasePassedActivity.onPassedBack(LockUpActivity.this, ivBack);
    }

    public void initView(){
        customSpinner = findViewById(R.id.spinner_lock_up);
        recyclerView = findViewById(R.id.list_lock_up_history);
    }

    public void initAction(){

    }

    private ArrayList<Subscription> getCustomList() {
        customList = new ArrayList<>();

        customList.add(new Subscription("Buy Back"));
        customList.add(new Subscription("Swap"));

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
        lockUpAdapter = new LockUpAdapter(DataLockUp.LIST_OF_LOCK_UP_HISTORY(),onClickLockUpHistory);
        arrayList = DataLockUp.LIST_OF_LOCK_UP_HISTORY();
        recyclerView.setAdapter(lockUpAdapter);
    }

    private final LockUpAdapter.OnClickLockUpHistory onClickLockUpHistory = new LockUpAdapter.OnClickLockUpHistory() {
        @Override
        public void clickMe(LockUpHistory lockUpHistory) {
            LockUpDetailDialog lockUpDetailDialog = LockUpDetailDialog.newInstance(lockUpHistory.getAmount(),lockUpHistory.getDate()
            ,lockUpHistory.getProject(),lockUpHistory.getPeriod(),lockUpHistory.getMaturity());

            lockUpDetailDialog.show(getSupportFragmentManager(),"faf");
        }
    };
}
