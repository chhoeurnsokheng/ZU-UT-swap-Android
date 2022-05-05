package com.zillennium.utswap.screens.wallet.subScriptionScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletSubscriptionBinding
import com.zillennium.utswap.Datas.ListDatas.subscriptionData.DataSubscription
import com.zillennium.utswap.models.Subscription.Subscription
import com.zillennium.utswap.models.SubscriptionHistory.SubscriptionHistory
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionAdapter
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionHistoryAdapter
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionHistoryAdapter.OnclickSubscriptionHistory
import com.zillennium.utswap.screens.wallet.subScriptionScreen.dialog.SubscriptionDetailDialog
import java.text.SimpleDateFormat
import java.util.*

class SubscriptionActivity :
    BaseMvpActivity<SubscriptionView.View, SubscriptionView.Presenter, ActivityWalletSubscriptionBinding>(),
    SubscriptionView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: SubscriptionView.Presenter = SubscriptionPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_subscription

    var calendar = Calendar.getInstance()

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val dateStart =
                    OnDateSetListener { _, year, month, day ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONDAY, month)
                        calendar.set(Calendar.DAY_OF_MONTH, day)
                        val format = "MM/dd/yyyy"
                        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                        edtxtFrom.setText(simpleDateFormat.format(calendar.time))
                    }

                val dateEnd =
                    OnDateSetListener { _, year, month, day ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONDAY, month)
                        calendar.set(Calendar.DAY_OF_MONTH, day)
                        val format = "MM/dd/yyyy"
                        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                        edtxtTo.setText(simpleDateFormat.format(calendar.time))
                    }

                edtxtFrom.setOnClickListener {
                    DatePickerDialog(
                        UTSwapApp.instance,
                        dateStart,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }

                edtxtTo.setOnClickListener {
                    DatePickerDialog(
                        UTSwapApp.instance,
                        dateEnd,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }

                val customList = getCustomList()
                val adapter = SubscriptionAdapter(UTSwapApp.instance, customList)
                spinnerSubscription.adapter = adapter
                spinnerSubscription.onItemSelectedListener = this@SubscriptionActivity

                // Set Passed Back
                ivBack.setOnClickListener { onBackPressed() }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun getCustomList(): ArrayList<Subscription> {
        val customList = ArrayList<Subscription>()
        customList.add(Subscription("UT New Airport 38Ha..."))
        customList.add(Subscription("UT Veng Sreng 2719..."))
        customList.add(Subscription("UT KT 1665..."))
        customList.add(Subscription("UT Muk Kampul 16644..."))
        customList.add(Subscription("UT Siem Reap 17140..."))
        return customList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
        val item = adapterView.selectedItem as Subscription
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    fun setAdapter() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(baseContext)
            listSubscriptionHistory.layoutManager = linearLayoutManager
            val subscriptionHistoryAdapter = SubscriptionHistoryAdapter(
                DataSubscription.LIST_OF_SUBSCRIPTION_HISTORY(),
                onclickSubscriptionHistory
            )
            listSubscriptionHistory.adapter = subscriptionHistoryAdapter
        }

    }

    private val onclickSubscriptionHistory =
        object : OnclickSubscriptionHistory {
            override fun onClickMe(subscriptionHistory: SubscriptionHistory?) {
                val subscriptionDetailDialog = SubscriptionDetailDialog.newInstance(
                    subscriptionHistory?.nameSub,
                    subscriptionHistory?.value,
                    subscriptionHistory?.date,
                    subscriptionHistory?.price,
                    subscriptionHistory?.volume,
                    subscriptionHistory?.transactionId
                )
                subscriptionDetailDialog.show(supportFragmentManager, "subscriptionDetailDialog")
            }
        }
}