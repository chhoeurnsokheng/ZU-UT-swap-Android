package com.zillennium.utswap.screens.systems.notificationScreen;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BasePassedActivity;
import com.zillennium.utswap.databinding.ActivitySystemNotificationBinding;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ActivitySystemNotificationBinding binding;

    @Nullable
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySystemNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            arrayList.add(new Notification(R.drawable.ut5, "Complete Register", "Zillion United welcomes you to our trading platform. Your trading account is being reviewed by our admin. Please be patient", "6 day ago"));
        }
        RecyclerView recyclerView = this.binding.rvNotification;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NotificationAdapter(arrayList));

        
        // Set Passed Back
        ImageView ivBack = binding.ivBack;
        BasePassedActivity.onPassedBack(NotificationActivity.this, ivBack);
    }

}
