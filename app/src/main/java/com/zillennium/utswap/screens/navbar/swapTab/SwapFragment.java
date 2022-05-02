package com.zillennium.utswap.screens.navbar.swapTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zillennium.utswap.databinding.FragmentNavbarSwapBinding;
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity;
import com.zillennium.utswap.screens.systems.notificationScreen.NotificationActivity;
import com.zillennium.utswap.screens.systems.scanQRCodeScreen.ScanQRCodeActivity;
import com.zillennium.utswap.screens.systems.searchScreen.SearchActivity;


public class SwapFragment extends Fragment {

    private FragmentNavbarSwapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SwapViewModel swapViewModel =
                new ViewModelProvider(this).get(SwapViewModel.class);

        binding = FragmentNavbarSwapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.binding.btnUser.setOnClickListener(view -> startActivity(new Intent(getActivity(), SettingActivity.class)));
        this.binding.btnQrcode.setOnClickListener(view -> startActivity(new Intent(getActivity(), ScanQRCodeActivity.class)));
        this.binding.btnSearch.setOnClickListener(view -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        this.binding.btnNotification.setOnClickListener(view -> startActivity(new Intent(getActivity(), NotificationActivity.class)));

        final TextView textView = binding.textSwap;
        swapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}