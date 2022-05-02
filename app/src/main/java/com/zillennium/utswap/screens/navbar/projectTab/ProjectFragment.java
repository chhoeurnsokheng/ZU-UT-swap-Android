package com.zillennium.utswap.screens.navbar.projectTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zillennium.utswap.databinding.FragmentNavbarProjectBinding;
import com.zillennium.utswap.screens.project.projecrDetailScreen.ProjectDetailActivity;
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity;
import com.zillennium.utswap.screens.systems.notificationScreen.NotificationActivity;
import com.zillennium.utswap.screens.systems.scanQRCodeScreen.ScanQRCodeActivity;
import com.zillennium.utswap.screens.systems.searchScreen.SearchActivity;

public class ProjectFragment extends Fragment {

    private FragmentNavbarProjectBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProjectViewModel projectViewModel =
                new ViewModelProvider(this).get(ProjectViewModel.class);

        binding = FragmentNavbarProjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnUser.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), SettingActivity.class));
        });

        binding.btnQrcode.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ScanQRCodeActivity.class));
        });
        binding.btnSearch.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), SearchActivity.class));
        });
        binding.btnNotification.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        });
        binding.layProject.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ProjectDetailActivity.class));
        });

//        final TextView textView = binding.textProject;
//        projectViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}