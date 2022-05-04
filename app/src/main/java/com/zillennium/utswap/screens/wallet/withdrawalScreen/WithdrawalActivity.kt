package com.zillennium.utswap.screens.wallet.withdrawalScreen

import android.view.View
import android.widget.AdapterView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletWithdrawalBinding
import com.zillennium.utswap.models.PaymentMethod.CustomItem
import com.zillennium.utswap.screens.wallet.depositScreen.adapter.CustomAdapter
import java.io.IOException

class WithdrawalActivity :
    BaseMvpActivity<WithdrawalView.View, WithdrawalView.Presenter, ActivityWalletWithdrawalBinding>(),
    WithdrawalView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: WithdrawalView.Presenter = WithdrawalPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_withdrawal

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val customList = getCustomList()
                val adapter = CustomAdapter(UTSwapApp.instance, customList)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this@WithdrawalActivity

                imgBack.setOnClickListener { finish() }

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }

    private fun getCustomList(): ArrayList<CustomItem> {
        val customList = ArrayList<CustomItem>()
        customList.add(
            CustomItem(
                "Choose your Receiving Address",
                R.drawable.ic_baseline_payment_24
            )
        )
        customList.add(CustomItem("Visa/Master Card", R.drawable.visa_card_logo))
        customList.add(CustomItem("ABA PAY", R.drawable.aba_logo))
        customList.add(CustomItem("ACLEDA Bank", R.drawable.acleda_logo))
        return customList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
        val item = adapterView.selectedItem as CustomItem
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

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