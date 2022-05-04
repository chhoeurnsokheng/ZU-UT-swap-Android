package com.zillennium.utswap.screens.wallet.historicalScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletHistoricalBinding
import com.zillennium.utswap.datas.historicalData.DataHistorical
import com.zillennium.utswap.models.Historical.Historical
import com.zillennium.utswap.models.Subscription.Subscription
import com.zillennium.utswap.screens.wallet.historicalScreen.adapter.HistoricalAdapter
import com.zillennium.utswap.screens.wallet.historicalScreen.adapter.HistoricalAdapter.OnClickHistoricalHistory
import com.zillennium.utswap.screens.wallet.historicalScreen.adapter.HistoricalFilterAdapter
import com.zillennium.utswap.screens.wallet.historicalScreen.dialog.HistoricalDetailDialog
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionAdapter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HistoricalActivity :
    BaseMvpActivity<HistoricalView.View, HistoricalView.Presenter, ActivityWalletHistoricalBinding>(),
    HistoricalView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: HistoricalView.Presenter = HistoricalPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_historical

    override fun initView() {
        super.initView()
//        try {
            binding.apply {

                initView()

                initAction()

                setAdapter()

                val filterList = getFilterList()
                val filterAdapter = HistoricalFilterAdapter(UTSwapApp.instance, filterList)
                spinnerFilter.adapter = filterAdapter
                spinnerFilter.onItemSelectedListener = this@HistoricalActivity

                val customList = getCustomList()
                val adapter = SubscriptionAdapter(UTSwapApp.instance, customList)
                spinnerHistorical.adapter = adapter
                spinnerHistorical.onItemSelectedListener = this@HistoricalActivity

                // Set Passed Back

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
//        } catch (error: IOException) {
//            // Must be safe
//        }
    }

    fun initAction() {
        binding.apply {
            val dateStart =
                OnDateSetListener { _, year, month, day ->
                    Calendar.getInstance().set(Calendar.YEAR, year)
                    Calendar.getInstance().set(Calendar.MONDAY, month)
                    Calendar.getInstance().set(Calendar.DAY_OF_MONTH, day)
                    val format = "MM/dd/yyyy"
                    val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                    edtxtFrom.setText(simpleDateFormat.format(Calendar.getInstance().time))
                }
            val dateEnd =
                OnDateSetListener { _, year, month, day ->
                    Calendar.getInstance().set(Calendar.YEAR, year)
                    Calendar.getInstance().set(Calendar.MONDAY, month)
                    Calendar.getInstance().set(Calendar.DAY_OF_MONTH, day)
                    val format = "MM/dd/yyyy"
                    val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                    edtxtTo.setText(simpleDateFormat.format(Calendar.getInstance().time))
                }
            edtxtFrom.setOnClickListener {
                DatePickerDialog(
                    UTSwapApp.instance,
                    dateStart,
                    Calendar.getInstance().get(
                        Calendar.YEAR
                    ),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            edtxtTo.setOnClickListener {
                DatePickerDialog(
                    UTSwapApp.instance,
                    dateEnd,
                    Calendar.getInstance().get(
                        Calendar.YEAR
                    ),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show()
            }
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

    private fun getFilterList(): ArrayList<Subscription> {
        val filterList = ArrayList<Subscription>()
        filterList.add(Subscription("-ALL-"))
        filterList.add(Subscription("Sold"))
        filterList.add(Subscription("Bought"))
        filterList.add(Subscription("Subscribe ITO"))
        filterList.add(Subscription("Sell ITO"))
        filterList.add(Subscription("Buy Back"))
        return filterList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
        val item = adapterView.selectedItem as Subscription
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    fun setAdapter() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(UTSwapApp.instance)
            listHistoricalHistory.layoutManager = linearLayoutManager
            val historicalAdapter =
                HistoricalAdapter(DataHistorical.LIST_OF_HISTORICAL_HISTORY(), onClickHistoricalHistory)
            listHistoricalHistory.adapter = historicalAdapter
        }

    }

    private val onClickHistoricalHistory =
        object : OnClickHistoricalHistory {
            override fun clickMe(historical: Historical?) {
                val historicalDetailDialog = HistoricalDetailDialog.newInstance(
                    historical?.nameSub,
                    historical?.date,
                    historical?.value,
                    historical?.volume,
                    historical?.price,
                    historical?.settlement,
                    historical?.utBalance
                )
                historicalDetailDialog.show(supportFragmentManager, "historicalDetailDialog")
            }
        }
}