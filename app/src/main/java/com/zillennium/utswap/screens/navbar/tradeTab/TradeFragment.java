package com.zillennium.utswap.screens.navbar.tradeTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding;
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity;
import com.zillennium.utswap.screens.systems.notificationScreen.NotificationActivity;
import com.zillennium.utswap.screens.systems.scanQRCodeScreen.ScanQRCodeActivity;
import com.zillennium.utswap.screens.systems.searchScreen.SearchActivity;
import com.zillennium.utswap.screens.trade.tradeExchangeScreen.TradeExchangeActivity;

public class TradeFragment extends Fragment {

    private FragmentNavbarTradeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TradeViewModel tradeViewModel =
                new ViewModelProvider(this).get(TradeViewModel.class);

        binding = FragmentNavbarTradeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // User
        ImageView btnUser = binding.btnUser;
        btnUser.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        });

        // Scan QR Code
        ImageView layQrcode = binding.btnQrcode;
        layQrcode.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ScanQRCodeActivity.class);
            startActivity(intent);
        });

        // Search
        RelativeLayout btnSearch = binding.btnSearch;
        btnSearch.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });

        // Notification
        ImageView btnNotification = binding.btnNotification;
        btnNotification.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            startActivity(intent);
        });

        LinearLayout layTrading = binding.layTrading;
        layTrading.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TradeExchangeActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}