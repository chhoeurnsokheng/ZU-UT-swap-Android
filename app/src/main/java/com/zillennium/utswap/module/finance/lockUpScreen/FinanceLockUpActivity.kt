package com.zillennium.utswap.module.finance.lockUpScreen

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceLockUpBinding
import com.zillennium.utswap.models.lockUpBalance.LockUpBalanceObject
import com.zillennium.utswap.module.finance.lockUpScreen.adapter.LockUpAdapter
import com.zillennium.utswap.module.finance.lockUpScreen.bottomSheet.FinanceLockUpBottomSheet
import com.zillennium.utswap.module.finance.lockUpScreen.dialog.FinanceLockUpDialog
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.isOnline

class FinanceLockUpActivity :
    BaseMvpActivity<FinanceLockUpView.View, FinanceLockUpView.Presenter, ActivityFinanceLockUpBinding>(),
    FinanceLockUpView.View {

    override var mPresenter: FinanceLockUpView.Presenter = FinanceLockUpPresenter()
    override val layoutResource: Int = R.layout.activity_finance_lock_up
    private var mList: ArrayList<Any> = arrayListOf()
    private var mAdapter: LockUpAdapter? = null
    private var page: Int = 1
    private var lastPosition = 0
    private var isLastPage = false
    private var totalPage = 1
    private var isLostConnection = false


    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        initToolBar()
        checkInternet()
        binding.apply {
            backImage.setOnClickListener {
                SettingVariable.finance_lock_up_selected.value = "Buy Back"

                finish()
            }
            layFilter.setOnClickListener {
                val financeLockUpBottomSheet: FinanceLockUpBottomSheet =
                    FinanceLockUpBottomSheet.newInstance(btnFilter.text.toString())
                financeLockUpBottomSheet.show(
                    supportFragmentManager, "Filter Lock Up"
                )
            }

            Tovuti.from(this@FinanceLockUpActivity).monitor { _, isConnected, _ ->
                if (isConnected && isLostConnection) {
                    requestData(false)
                    rvFilterLockUp.visibility = View.VISIBLE
                    tvNoLockUp.visibility = View.GONE
                    isLostConnection = false
                }
            }

            swipeRefresh.setOnRefreshListener {
                page = 1
                requestData(false)
                checkInternet()

            }

        }

        requestData(false)
        initRecyclerView()
        loadMoreData()
    }

    private fun checkInternet() {
        binding.apply {
            if (isOnline(this@FinanceLockUpActivity)) {
                rvFilterLockUp.visibility = View.VISIBLE
                tvNoLockUp.visibility = View.GONE

            } else {
                rvFilterLockUp.visibility = View.GONE
                tvNoLockUp.visibility = View.VISIBLE
                tvNoLockUp.text = "No Internet Connection"
                isLostConnection = true
                swipeRefresh.isRefreshing = false

            }
        }
    }
    private fun initToolBar() {
        setSupportActionBar(binding.toolBar.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolBar.apply {
            tbTitle.setText(R.string.lock_up)
            tb.setNavigationOnClickListener {
                SettingVariable.finance_lock_up_selected.value = "Buy Back"
                finish()
            }
        }
    }
    private fun initRecyclerView() {
        binding.apply {
            mAdapter = LockUpAdapter(mList, object : LockUpAdapter.OnClickAdapter{
                override fun onClickMe(
                    projectName: String,
                    amount: Double,
                    start: String,
                    end: String,
                    duration: Int,
                    isUnLock: Boolean
                ) {
                    when (SettingVariable.finance_lock_up_selected.value.toString()) {
                        "Lock Balance" -> {
                            val financeLockUpDialog: FinanceLockUpDialog =
                                FinanceLockUpDialog.newInstance(
                                    projectName,
                                    amount,
                                    start,
                                    end,
                                    duration.toString(),
                                    "Lock Balance",
                                    isUnLock
                                )
                            financeLockUpDialog.show(
                                supportFragmentManager,
                                "Lock Up History"
                            )

                        }
                        "Buy Back" -> {
                            val financeLockUpDialog: FinanceLockUpDialog =
                                FinanceLockUpDialog.newInstance(
                                    projectName,
                                    amount,
                                    start,
                                    end,
                                    duration.toString(),
                                    "Buy Back",
                                    isUnLock
                                )
                            financeLockUpDialog.show(supportFragmentManager, "Lock Up History")


                        }
                        "Swap" -> {
                            val financeLockUpDialog: FinanceLockUpDialog =
                                FinanceLockUpDialog.newInstance(
                                    projectName,
                                    amount,
                                    start,
                                    end,
                                    duration.toString(),
                                    "Swap",
                                    isUnLock
                                )
                            financeLockUpDialog.show(supportFragmentManager, "Lock Up History")
                        }

                    }
                }
            })

            rvFilterLockUp.apply {
                layoutManager = LinearLayoutManager(
                    this@FinanceLockUpActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = mAdapter

            }

        }

    }

    private fun requestData(fromLoadMore: Boolean) {
        SettingVariable.finance_lock_up_selected.observe(this@FinanceLockUpActivity) {
            binding.btnFilter.text = SettingVariable.finance_lock_up_selected.value.toString()
            if (!fromLoadMore) {
                page = 1
            }
            when (SettingVariable.finance_lock_up_selected.value.toString()) {
                "Lock Balance" -> {
                    mPresenter.postLockUpBalance(4, page)
                }
                "Buy Back" -> {
                    mPresenter.postLockUpBalance(2, page)

                }
                "Swap" -> {
                    mPresenter.postLockUpBalance(1, page)

                }
            }
        }
    }

    private fun loadMoreData() {
        binding.rvFilterLockUp.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    lastPosition =
                        (binding.rvFilterLockUp.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastPosition == mList.size - 1 && page < totalPage) {
                        binding.progressBar.visibility = View.VISIBLE
                        page++
                        requestData(true)
                    }


                }
            }
        })

    }

    override fun onPostLockUpBalanceSuccess(dataRes: LockUpBalanceObject.LockUpBalanceResData) {
        if (page == 1) {
            mList.clear()
        }
        totalPage = dataRes.total_page
        mList.addAll(dataRes.transaction)
        if (dataRes.total_lock_balance.isNotEmpty()) {
            binding.amountLockUp.text =
                "$ ${groupingSeparator(dataRes.total_lock_balance.toDouble())}"
        } else {
            binding.amountLockUp.text = "$ 0.00"
        }
        binding.tvNoLockUp.visibility =
            if (dataRes.transaction.isEmpty()) View.VISIBLE else View.GONE
        binding.tvNoLockUp.text = "No Record"
        isLastPage = dataRes.transaction.size == 10
        binding.progressBar.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
        mAdapter?.notifyDataSetChanged()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        SettingVariable.finance_lock_up_selected.value = "Buy Back"

    }

    override fun onPostLockBalanceFail() {
    }

}