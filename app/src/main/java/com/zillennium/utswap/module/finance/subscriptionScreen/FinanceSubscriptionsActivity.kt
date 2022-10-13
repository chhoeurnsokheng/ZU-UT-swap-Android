package com.zillennium.utswap.module.finance.subscriptionScreen

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceSubscriptionsBinding
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import com.zillennium.utswap.module.finance.subscriptionScreen.adapter.FinanceSubscriptionsAdapter
import com.zillennium.utswap.module.finance.subscriptionScreen.bottomSheet.FinanceSubscriptionDateRangeBottomSheet
import com.zillennium.utswap.module.finance.subscriptionScreen.bottomSheet.FinanceSubscriptionFilterBottomSheet
import com.zillennium.utswap.module.finance.subscriptionScreen.dialog.FinanceSubscriptionsDialog
import com.zillennium.utswap.utils.isOnline


class FinanceSubscriptionsActivity :
    BaseMvpActivity<FinanceSubscriptionsView.View, FinanceSubscriptionsView.Presenter, ActivityFinanceSubscriptionsBinding>(),
    FinanceSubscriptionsView.View {

    override var mPresenter: FinanceSubscriptionsView.Presenter = FinanceSubscriptionsPresenter()
    override val layoutResource: Int = R.layout.activity_finance_subscriptions
    private val arraySubscription = ArrayList<SubscriptionObject.SubscriptionList>()
    private var listProject: ArrayList<SubscriptionObject.ProjectList> = arrayListOf()
    private var financeSubscriptionsAdapter: FinanceSubscriptionsAdapter? = null
    private var page = 1
    private var filterBottomSheet: FinanceSubscriptionFilterBottomSheet? = null
    private var projectName = ""
    private var start = ""
    private var end = ""
    private var totalItem = ""
    private var lastPosition = 0
    private var dateStart: String = ""
    private var dateEnd: String = ""
    private var isLostConnection = false

    companion object {
        var titleProject = "All Projects"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        toolBar()
        checkInternet()

        binding.apply {

            /* Filter */
            layFilterButton.setOnClickListener {
                filterBottomSheet = FinanceSubscriptionFilterBottomSheet(listProject)
                filterBottomSheet?.show(supportFragmentManager, "Filter Subscription")
            }

            /* Select Date Range */
            laySelectDateRange.setOnClickListener {

                val financeHistoricalSelectDateRangeBottomSheet = FinanceSubscriptionDateRangeBottomSheet(
                    object: FinanceSubscriptionDateRangeBottomSheet.CallBackDateListener{
                        override fun onSelectDateChangeSelect(startDate: String, endDate: String) {

                            dateStart = startDate
                            dateEnd = endDate
                            if (endDate.isNotEmpty()){
                                txtSelectedDateStart.visibility = View.VISIBLE
                                txtSelectedDateEnd.visibility = View.VISIBLE
                                txtSelectDateTo.visibility = View.VISIBLE
                                txtSelectDateFromTo.visibility = View.GONE
                                txtSelectedDateStart.text = dateStart
                                txtSelectedDateEnd.text = dateEnd
                            }

                        }
                    }
                )
                financeHistoricalSelectDateRangeBottomSheet.show(supportFragmentManager, "Select Date Time Subscription")
            }

            swipeRefresh.setOnRefreshListener {
                checkInternet()
                page = 1
                requestData()
            }

            Tovuti.from(this@FinanceSubscriptionsActivity).monitor { _, isConnected, _ ->
                if (isConnected && isLostConnection) {
                    requestData()
                    rvSubscriptions.visibility = View.VISIBLE
                    tvNoRecord.visibility = View.GONE
                    isLostConnection = false
                }
            }

            SettingVariable.finance_subscription_filter.observe(this@FinanceSubscriptionsActivity) {
                projectName = it
                requestData()
            }

            SettingVariable.finance_subscription_filter_project_name.observe(this@FinanceSubscriptionsActivity) {
                titleProject = it
                txtTitleFilter.text = it

            }

            SettingVariable.finance_subscription_date_end.observe(this@FinanceSubscriptionsActivity) {
                end = it
                if (start.isNotEmpty()) {
                    requestData()
                }
            }
            SettingVariable.finance_subscription_date_start.observe(this@FinanceSubscriptionsActivity) {
                start = it
                if (end.isNotEmpty()) {
                    requestData()
                }

            }
        }
        requestData()
        initRecyclerView()
        loadMoreData()
    }

    private fun requestData() {
        val obj = SubscriptionObject.SubscriptionBody()
        obj.project = projectName
        obj.end = end
        obj.start = start
        obj.order_page = page
        mPresenter.postSubscription(obj)

    }

    private fun checkInternet() {
        binding.apply {
            if (isOnline(this@FinanceSubscriptionsActivity)) {
                rvSubscriptions.visibility = View.VISIBLE
                tvNoRecord.visibility = View.GONE

            } else {
                rvSubscriptions.visibility = View.GONE
                tvNoRecord.visibility = View.VISIBLE
                tvNoRecord.text = "No Internet Connection"
                isLostConnection = true
                swipeRefresh.isRefreshing = false

            }
        }
    }

    private fun loadMoreData() {
        binding.rvSubscriptions.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    lastPosition = (binding.rvSubscriptions.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastPosition == arraySubscription.size - 1 && arraySubscription.size < totalItem.toInt()) {
                        binding.progressBar.visibility = View.VISIBLE
                        page++
                        requestData()
                    }

                }
            }
        })

    }

    override fun onPostSubscriptionSuccess(dataRes: SubscriptionObject.SubscriptionRes) {
        totalItem = dataRes.total_transactions
        if (page == 1) {
            arraySubscription.clear()
        }
        arraySubscription.addAll(dataRes.transaction)
        listProject.clear()
        val allProjectObj = SubscriptionObject.ProjectList()
        allProjectObj.name = "All Projects"
        listProject.add(allProjectObj)

        listProject.sortBy {
            it.name
        }

        if (dataRes.transaction.isEmpty()) {
            binding.tvNoRecord.visibility = View.VISIBLE
            binding.rvSubscriptions.visibility = View.GONE
        } else {
            binding.tvNoRecord.visibility = View.GONE
            binding.rvSubscriptions.visibility = View.VISIBLE

        }
        binding.tvNoRecord.text = "No Record"
        binding.progressBar.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
        listProject.addAll(dataRes.market_list)
        financeSubscriptionsAdapter?.notifyDataSetChanged()

    }

    override fun onPostSubscriptionFail() {
    }


    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_primary)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.subscriptions)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun initRecyclerView() {
        financeSubscriptionsAdapter =
            FinanceSubscriptionsAdapter(
                arraySubscription,
                object : FinanceSubscriptionsAdapter.OnClickAdapter {
                    override fun onSubscriptionItemClick(
                        title: String,
                        isLock: Boolean,
                        tranId: String,
                        price: Double,
                        volume: String,
                        value: Double,
                        start: String,
                        end: String,
                        duration: Int
                    ) {
                        val financeSubscriptionsDialog: FinanceSubscriptionsDialog =
                            FinanceSubscriptionsDialog.newInstance(
                                title,
                                isLock,
                                tranId,
                                price,
                                volume,
                                value,
                                start,
                                end,
                                duration
                            )
                        financeSubscriptionsDialog.show(
                            supportFragmentManager,
                            "Dialog of Subscription"
                        )

                    }

                })
        binding.rvSubscriptions.apply {
            layoutManager = LinearLayoutManager(
                this@FinanceSubscriptionsActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = financeSubscriptionsAdapter
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        SettingVariable.finance_subscription_date_start.value = ""
        SettingVariable.finance_subscription_date_end.value = ""
        SettingVariable.finance_subscription_filter.value = ""
        SettingVariable.finance_subscription_date_end.value = ""
        SettingVariable.finance_subscription_filter_project_name.value = "All Projects"
//        SettingVariable.finance_subscription_filter.removeObservers(this@FinanceSubscriptionsActivity)
//        SettingVariable.finance_subscription_date_end.removeObservers(this@FinanceSubscriptionsActivity)
    }

}