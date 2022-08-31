package com.zillennium.utswap.module.finance.withdrawScreen.addBank

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ActivityFinanceAddBankBinding
import com.zillennium.utswap.models.WithdrawAddbankModel
import com.zillennium.utswap.models.withdraw.WithdrawObj
import com.zillennium.utswap.module.finance.withdrawScreen.adapter.WithdrawAdapter
import com.zillennium.utswap.module.finance.withdrawScreen.withdrawBottomSheet.BottomSheetFinanceAddBank


class AddBankActivity :
    BaseMvpActivity<AddBankView.View, AddBankView.Presenter, ActivityFinanceAddBankBinding>(),
    AddBankView.View {

    override var mPresenter: AddBankView.Presenter = AddBankPresenter()
    override val layoutResource: Int = R.layout.activity_finance_add_bank

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<WithdrawAddbankModel>
    lateinit var imageBank: Array<Int>
    lateinit var titleBank: Array<String>
    private var withdrawAdapter: WithdrawAdapter? = null

    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    override fun initView() {
        super.initView()
        try {
            mPresenter.getListAvailableWithdrawBank(this)
            toolBar()
        } catch (error: Exception) {
            // Must be safe
        }
    }
    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.add_bank_account)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

    override fun onGetListAvailableWithdrawBankSuccess(data: WithdrawObj.WithdrawRes) {
        binding.apply {
            rvAddBank.apply {
                layoutManager = LinearLayoutManager(this@AddBankActivity)
                withdrawAdapter = data.data?.let { WithdrawAdapter(it, onClickWithdrawBank) }
                adapter  = withdrawAdapter
            }
        }
    }

    override fun onGetListAvailableWithdrawBankFailed(data: String) {}
    
    private val onClickWithdrawBank: WithdrawAdapter.OnClickBank =
        object : WithdrawAdapter.OnClickBank {

            override fun clickWithdrawBank(titleBank: String?, imgBank: String?) {
                when (titleBank.toString()) {
                    "ABA Pay" -> {

                        val withdrawBankDialog = BottomSheetFinanceAddBank.newInstance(titleBank, imgBank)
                        withdrawBankDialog.show(this@AddBankActivity.supportFragmentManager, "Deposit Dialog"
                        )
                    }
                    "Acleda Bank" -> {

                        val withdrawBankDialog =
                            BottomSheetFinanceAddBank.newInstance(titleBank.toString(), imgBank)
                        withdrawBankDialog.show(
                            this@AddBankActivity.supportFragmentManager, "Deposit Dialog"
                        )
                    }
                    "Sathapana" -> {

                        val withdrawBankDialog = BottomSheetFinanceAddBank.newInstance(titleBank.toString(), imgBank)
                        withdrawBankDialog.show(
                            this@AddBankActivity.supportFragmentManager, "Deposit Dialog"
                        )
                    }
                }
            }

        }
}