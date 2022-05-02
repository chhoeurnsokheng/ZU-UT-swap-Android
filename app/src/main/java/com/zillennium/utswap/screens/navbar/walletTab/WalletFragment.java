package com.zillennium.utswap.screens.navbar.walletTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zillennium.utswap.R;
import com.zillennium.utswap.bases.BaseListViewHeightActivity;
import com.zillennium.utswap.databinding.FragmentNavbarWalletBinding;
import com.zillennium.utswap.models.ListMenu.ListMenu;
import com.zillennium.utswap.models.ListMenu.ListMenuAdapter;
import com.zillennium.utswap.screens.setting.MyWallet.MyWalletActivity;
import com.zillennium.utswap.screens.setting.accountTypeScreen.AccountTypeActivity;
import com.zillennium.utswap.screens.setting.balanceScreen.BalanceActivity;
import com.zillennium.utswap.screens.setting.depositScreen.DepositActivity;
import com.zillennium.utswap.screens.setting.historicalScreen.HistoricalActivity;
import com.zillennium.utswap.screens.setting.kycScreen.KYCActivity;
import com.zillennium.utswap.screens.setting.lockUpScreen.LockUpActivity;
import com.zillennium.utswap.screens.setting.profileQRCodeScreen.ProfileQRCodeActivity;
import com.zillennium.utswap.screens.setting.profileScreen.ProfileActivity;
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity;
import com.zillennium.utswap.screens.setting.subScriptionScreen.SubscriptionActivity;
import com.zillennium.utswap.screens.setting.termScreen.TermActivity;
import com.zillennium.utswap.screens.setting.transferScreen.TransferActivity;
import com.zillennium.utswap.screens.setting.withdrawalScreen.WithdrawalActivity;

import java.util.ArrayList;

public class WalletFragment extends Fragment {

    private FragmentNavbarWalletBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WalletViewModel walletViewModel =
                new ViewModelProvider(this).get(WalletViewModel.class);

        binding = FragmentNavbarWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set Activity Account
        //Name Account
        TextView txtAccount = binding.txtAccount;
        txtAccount.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        });

        // Profile QR Code
        LinearLayout layQrcode = binding.layQrcode;
        layQrcode.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ProfileQRCodeActivity.class);
            startActivity(intent);
        });

        // Picture Account
        com.google.android.material.imageview.ShapeableImageView ivProfile = binding.ivProfile;
        ivProfile.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        });
        // Account Type
        TextView txtAccountType = binding.txtAccountType;
        txtAccountType.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AccountTypeActivity.class);
            startActivity(intent);
        });

        // Verify
        LinearLayout txtVerify = binding.btnVerify;
        txtVerify.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), KYCActivity.class);
            startActivity(intent);
        });

        // Set Activity Setting
        ImageView btnSetting = binding.btnSetting;
        btnSetting.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        });

        LinearLayout layWallet = binding.layWallet;
        layWallet.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MyWalletActivity.class);
            startActivity(intent);
        });

        // Set Balance ListView
//        int[] icon_balance = {R.drawable.ic_tasks_alt_regular,
//                R.drawable.ic_share_regular,
//                R.drawable.ic_reply_regular,
//                R.drawable.ic_paper_plane_regular,
//                R.drawable.ic_hourglass_empty_regular,
//                R.drawable.ic_lock_keyhole_regular,
//                R.drawable.ic_cart_plus_regular};
//        String[] title_balance = {"Balance",
//                "Deposit",
//                "Withdrawal",
//                "Transfer",
//                "Subscription",
//                "Lock-up",
//                "Historical"};
        int[] icon_balance = {
                R.drawable.ic_hourglass_empty_regular,
                R.drawable.ic_lock_keyhole_regular,
                R.drawable.ic_cart_plus_regular};
        String[] title_balance = {
                "Subscription",
                "Lock-up",
                "Historical"};

        ArrayList<ListMenu> accountArrayList = new ArrayList<>();

        for(int i=0; i<title_balance.length; i++){
            ListMenu account = new ListMenu(title_balance[i], icon_balance[i]);
            accountArrayList.add(account);
        }

        ListMenuAdapter accountAdapter = new ListMenuAdapter(getActivity(), accountArrayList);

        ListView lvBalance = binding.lvBalance;
        lvBalance.setAdapter(accountAdapter);
        lvBalance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent;

                String title = title_balance[position];

                switch (title){
                    case "Deposit":
                        intent = new Intent(view.getContext(), DepositActivity.class);
                        break;
                    case "Withdrawal":
                        intent = new Intent(view.getContext(), WithdrawalActivity.class);
                        break;
                    case "Transfer":
                        intent = new Intent(view.getContext(), TransferActivity.class);
                        break;
                    case "Subscription":
                        intent = new Intent(view.getContext(), SubscriptionActivity.class);
                        break;
                    case "Lock-up":
                        intent = new Intent(view.getContext(), LockUpActivity.class);
                        break;
                    case "Historical":
                        intent = new Intent(view.getContext(), HistoricalActivity.class);
                        break;
                    default: intent = new Intent(view.getContext(), BalanceActivity.class); // As Balance
                }

                startActivity(intent);
            }
        });
        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvBalance);

        // Set Terms ListView
        int[] icon_terms = {R.drawable.ic_gem_regular,
                R.drawable.ic_gem_regular,
                R.drawable.ic_gem_regular};
        String[] title_terms = {"Term and Conditions",
                "Privacy Policy",
                "Operation Rules"};

        ArrayList<ListMenu> termsArrayList = new ArrayList<>();

        for(int i=0; i<title_terms.length; i++){
            ListMenu term = new ListMenu(title_terms[i], icon_terms[i]);
            termsArrayList.add(term);
        }

        ListMenuAdapter termsAdapter = new ListMenuAdapter(getActivity(), termsArrayList);

        ListView lvTerm = binding.lvTerm;
        lvTerm.setAdapter(termsAdapter);
        lvTerm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TermActivity.class);

                String title = title_terms[position];

                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvTerm);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}