package com.zillennium.utswap.screens.wallet.lockUpScreen

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BasePassedActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletLockUpBinding
import com.zillennium.utswap.datas.lockUpData.DataLockUp
import com.zillennium.utswap.models.LockUpHistory.LockUpHistory
import com.zillennium.utswap.models.Subscription.Subscription
import com.zillennium.utswap.screens.wallet.lockUpScreen.adapter.LockUpAdapter
import com.zillennium.utswap.screens.wallet.lockUpScreen.adapter.LockUpAdapter.OnClickLockUpHistory
import com.zillennium.utswap.screens.wallet.lockUpScreen.dialog.LockUpDetailDialog
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionAdapter
import java.io.IOException

class LockUpActivity :
    BaseMvpActivity<LockUpView.View, LockUpView.Presenter, ActivityWalletLockUpBinding>(),
    LockUpView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: LockUpView.Presenter = LockUpPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_lock_up

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                setAdapter()

                val customList = getCustomList()
                val adapter = SubscriptionAdapter(UTSwapApp.instance, customList)
                spinnerLockUp.adapter = adapter
                spinnerLockUp.onItemSelectedListener = this@LockUpActivity

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun getCustomList(): ArrayList<Subscription> {
        val customList = ArrayList<Subscription>()
        customList.add(Subscription("Buy Back"))
        customList.add(Subscription("Swap"))
        return customList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
        val item = adapterView.selectedItem as Subscription
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    fun setAdapter() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(baseContext)
            listLockUpHistory.layoutManager = linearLayoutManager
            val lockUpAdapter = LockUpAdapter(DataLockUp.LIST_OF_LOCK_UP_HISTORY(), onClickLockUpHistory)
            listLockUpHistory.adapter = lockUpAdapter
        }

    }

    private val onClickLockUpHistory =
        object : OnClickLockUpHistory {
            override fun clickMe(lockUpHistory: LockUpHistory?) {
                val lockUpDetailDialog = LockUpDetailDialog.newInstance(
                    lockUpHistory?.amount,
                    lockUpHistory?.date,
                    lockUpHistory?.project,
                    lockUpHistory?.period,
                    lockUpHistory?.maturity
                )
                lockUpDetailDialog.show(supportFragmentManager, "lockUpDetailDialog")
            }
        }
}