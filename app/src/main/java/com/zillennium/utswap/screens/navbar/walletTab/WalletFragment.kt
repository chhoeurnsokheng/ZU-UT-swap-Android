package com.zillennium.utswap.screens.navbar.walletTab

import android.content.Intent
import android.widget.AdapterView.OnItemClickListener
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarWalletBinding
import com.zillennium.utswap.models.ListMenu.ListMenu
import com.zillennium.utswap.models.ListMenu.ListMenuAdapter
import com.zillennium.utswap.screens.setting.kyc.KYCActivity
import com.zillennium.utswap.screens.wallet.transactionScreen.TransactionActivity
import com.zillennium.utswap.screens.setting.accountTypeScreen.AccountTypeActivity
import com.zillennium.utswap.screens.setting.balanceScreen.BalanceActivity
import com.zillennium.utswap.screens.wallet.depositScreen.DepositActivity
import com.zillennium.utswap.screens.wallet.historicalScreen.HistoricalActivity
import com.zillennium.utswap.screens.wallet.lockUpScreen.LockUpActivity
import com.zillennium.utswap.screens.setting.profileQRCodeScreen.ProfileQRCodeActivity
import com.zillennium.utswap.screens.setting.profileScreen.ProfileActivity
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity
import com.zillennium.utswap.screens.wallet.subScriptionScreen.SubscriptionActivity
import com.zillennium.utswap.screens.privacy.termScreen.TermActivity
import com.zillennium.utswap.screens.wallet.transferScreen.TransferActivity
import com.zillennium.utswap.screens.wallet.withdrawalScreen.WithdrawalActivity

class WalletFragment :
    BaseMvpFragment<WalletView.View, WalletView.Presenter, FragmentNavbarWalletBinding>(),
    WalletView.View {

    override var mPresenter: WalletView.Presenter = WalletPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_wallet

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Activity Account
                //Name Account
                txtAccount.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ProfileActivity::class.java)
                    startActivity(intent)
                }

                // Profile QR Code
                layQrcode.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ProfileQRCodeActivity::class.java)
                    startActivity(intent)
                }

                // Picture Account
                ivProfile.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ProfileActivity::class.java)
                    startActivity(intent)
                }

                // Account Type
                txtAccountType.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, AccountTypeActivity::class.java)
                    startActivity(intent)
                }

                // Verify
                btnVerify.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                    startActivity(intent)
                }

                // Set Activity Setting
                btnSetting.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SettingActivity::class.java)
                    startActivity(intent)
                }

                // Layout Wallet
                layWallet.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, TransactionActivity::class.java)
                    startActivity(intent)
                }

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
                val iconBalance = intArrayOf(
                    R.drawable.ic_hourglass_empty_regular,
                    R.drawable.ic_lock_keyhole_regular,
                    R.drawable.ic_cart_plus_regular
                )
                val titleBalance = arrayOf(
                    "Subscription",
                    "Lock-up",
                    "Historical"
                )

                val accountArrayList = ArrayList<ListMenu>()

                for (i in titleBalance.indices) {
                    val account = ListMenu(titleBalance[i], iconBalance[i])
                    accountArrayList.add(account)
                }

                val accountAdapter = ListMenuAdapter(UTSwapApp.instance, accountArrayList)

                val lvBalance = binding.lvBalance
                lvBalance.adapter = accountAdapter
                lvBalance.onItemClickListener =
                    OnItemClickListener { _, _, position, _ ->
                        val intent: Intent
                        val title = titleBalance[position]
                        intent = when (title) {
                            "Deposit" -> Intent(UTSwapApp.instance, DepositActivity::class.java)
                            "Withdrawal" -> Intent(UTSwapApp.instance, WithdrawalActivity::class.java)
                            "Transfer" -> Intent(UTSwapApp.instance, TransferActivity::class.java)
                            "Subscription" -> Intent(UTSwapApp.instance, SubscriptionActivity::class.java)
                            "Lock-up" -> Intent(UTSwapApp.instance, LockUpActivity::class.java)
                            "Historical" -> Intent(UTSwapApp.instance, HistoricalActivity::class.java)
                            else -> Intent(UTSwapApp.instance, BalanceActivity::class.java) // As Balance
                        }
                        startActivity(intent)
                    }
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvBalance)

                // Set Terms ListView
                val iconTerms = intArrayOf(
                    R.drawable.ic_gem_regular,
                    R.drawable.ic_gem_regular,
                    R.drawable.ic_gem_regular
                )
                val titleTerms = arrayOf(
                    "Term and Conditions",
                    "Privacy Policy",
                    "Operation Rules"
                )

                val termsArrayList = ArrayList<ListMenu>()

                for (i in titleTerms.indices) {
                    val term = ListMenu(titleTerms[i], iconTerms[i])
                    termsArrayList.add(term)
                }

                val termsAdapter = ListMenuAdapter(UTSwapApp.instance, termsArrayList)

                lvTerm.adapter = termsAdapter
                lvTerm.onItemClickListener =
                    OnItemClickListener { _, _, position, _ ->
                        val intent = Intent(UTSwapApp.instance, TermActivity::class.java)
                        val title = titleTerms[position]
                        intent.putExtra("title", title)
                        startActivity(intent)
                    }

                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvTerm)

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}