package com.zillennium.utswap.screens.setting.MyWallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zillennium.utswap.R;
import com.zillennium.utswap.screens.setting.MyWallet.dialog.ExportTransactionBottomSheetDialog;
import com.zillennium.utswap.screens.setting.MyWallet.dialog.FilterBottomSheetDialog;
import com.zillennium.utswap.screens.setting.depositScreen.DepositActivity;
import com.zillennium.utswap.screens.setting.transferScreen.TransferActivity;
import com.zillennium.utswap.screens.setting.withdrawalScreen.WithdrawalActivity;

import java.util.ArrayList;


public class MyWalletActivity extends AppCompatActivity {

    private ImageView imgBack, imgExport, imgFilter, imgDeposit, imgWithdrawal, imgMore, imgTransfer, imgDropDown;
    private RecyclerView recyclerView;
    private LinearLayout linearAllWallet;
    private int count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_transaction);

        initView();

        initAction();

        setAdapter();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        imgExport = findViewById(R.id.imgExport);
        imgFilter = findViewById(R.id.imgFilter);
        imgDeposit = findViewById(R.id.img_deposit);
        imgTransfer = findViewById(R.id.img_transfer);
        imgWithdrawal = findViewById(R.id.img_withdrawal);
        recyclerView = findViewById(R.id.list_transaction);
        imgDropDown = findViewById(R.id.img_drop_down);
        linearAllWallet = findViewById(R.id.linear_all_wallet);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {finish();});

        imgFilter.setOnClickListener(view -> {
            FilterBottomSheetDialog a = FilterBottomSheetDialog.newInstance();
            a.show(getSupportFragmentManager(),"Open_fragment");
        });

        imgExport.setOnClickListener(view -> {
            ExportTransactionBottomSheetDialog a = ExportTransactionBottomSheetDialog.newInstance();
            a.show(getSupportFragmentManager(),"Open_fragment");
        });

        imgWithdrawal.setOnClickListener(view -> {
            Intent intent = new Intent(this, WithdrawalActivity.class);
            startActivity(intent);
        });

        imgTransfer.setOnClickListener(view -> {
            Intent intent = new Intent(this, TransferActivity.class);
            startActivity(intent);
        });

        imgDeposit.setOnClickListener(view -> {
            Intent intent = new Intent(this, DepositActivity.class);
            startActivity(intent);
        });

        imgDropDown.setOnClickListener(view -> {
            openDropDown(count);
            count++;
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void openDropDown(int count){
        if(count % 2 == 0)
        {
            linearAllWallet.setVisibility(View.GONE);
            imgDropDown.setImageDrawable(getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24));
        }
        else
        {
            linearAllWallet.setVisibility(View.VISIBLE);
            imgDropDown.setImageDrawable(getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24));
        }


    }

    public void setAdapter(){

        ArrayList<String> arrayDay = new ArrayList<>();

        String dayCurrent = "";
        ArrayList<MyWalletGroup> dataGroupList = new ArrayList<MyWalletGroup>();
        ArrayList<MyWalletItem> dataItemList = new ArrayList<MyWalletItem>();

        ArrayList<MyWalletItem> dataBalance = DataMyWallet.LIST_OF_BALANCE_HISTORY();

        for (int i = 0; i < dataBalance.size(); i++){
            MyWalletItem data = dataBalance.get(i);

            if(i == 0 || i == dataBalance.size() || dayCurrent.equals("") || data.getDate() != dayCurrent){
                if(i != 0){
                    dataGroupList.add(new MyWalletGroup(dayCurrent, dataItemList));
                    dataItemList = new ArrayList<>();
                }
            }
            dayCurrent = data.getDate();
            dataItemList.add(data);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyWalletGroupAdapter myWalletGroupAdapter = new MyWalletGroupAdapter(dataGroupList);
        recyclerView.setAdapter(myWalletGroupAdapter);
    }

}