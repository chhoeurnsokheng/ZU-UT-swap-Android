package com.zillennium.utswap.screens.setting.transferScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zillennium.utswap.R;

public class TransferActivity extends AppCompatActivity {

    private ImageView imgBack;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_transfer);

        initView();

        initAction();

    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {finish();});
    }

//    public void setAdapter(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        transferAdapter = new TransferAdapter(DataTransferHistory.LIST_OF_TRANSFER_HISTORY(),onclickTransferHistory);
//        arrayList = DataTransferHistory.LIST_OF_TRANSFER_HISTORY();
//        recyclerView.setAdapter(transferAdapter);
//    }
//
//    private final TransferAdapter.OnclickTransferHistory onclickTransferHistory = transferHistory -> {
//        TransferHistoryDetailDialog transferHistoryDetailDialog = TransferHistoryDetailDialog.newInstance(transferHistory.getTransactionId(),transferHistory.getTime()
//        ,transferHistory.getSender(),transferHistory.getReceiver(),transferHistory.getAmount(),transferHistory.getTotal());
//
//        transferHistoryDetailDialog.show(getSupportFragmentManager(),"afag");
//    };
}
