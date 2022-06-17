package com.zillennium.utswap.screens.finance.withdrawScreen.addBank

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ActivityFinanceAddBankBinding
import com.zillennium.utswap.models.WithdrawAddbankModel
import com.zillennium.utswap.screens.finance.withdrawScreen.adapter.WithdrawAdapter
import com.zillennium.utswap.screens.finance.withdrawScreen.withdrawBottomSheet.BottomSheetFinanceAddBank


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

            binding.apply {

                layNavbar.txtTitle.text = resources.getString(R.string.add_bank_address)
                layNavbar.imgBack.setOnClickListener {
                    finish()
                }


                imageBank = arrayOf(
                    R.drawable.aba_pay,
                    R.drawable.acleda,
                    R.drawable.sathapana
                )

                titleBank = arrayOf(
                    "ABA Pay",
                    "Acleda Bank",
                    "Sathapana"
                )

                newArrayList = arrayListOf<WithdrawAddbankModel>()
                for (i in imageBank.indices) {
                    val withdrawBank = WithdrawAddbankModel(
                        imageBank[i],
                        titleBank[i]

                    )
                    newArrayList.add(withdrawBank)
                }
                rvAddBank.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                withdrawAdapter = WithdrawAdapter(newArrayList, onClickWithdrawBank)
                rvAddBank.adapter = withdrawAdapter
            }

//            binding.addBank.imgMenu.setOnClickListener {
//                onBackPressed()
//                val item = WithdrawAddbankModel(imageBank.size.toInt(), titleBank.toString())
//                SessionVariable.SESSION_BANK.value?.toMutableList()?.add(item)
//            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onClickWithdrawBank: WithdrawAdapter.OnClickBank =
        object : WithdrawAdapter.OnClickBank {
            override fun clickWithdrawBank(titleBank: String, imgBank: Int) {
                when (titleBank.toString()) {
                    "ABA Pay" -> {

                        val withdrawBankDialog =
                            BottomSheetFinanceAddBank.newInstance(titleBank.toString(), imgBank)
                        withdrawBankDialog.show(
                            this@AddBankActivity.supportFragmentManager, "Deposit Dialog"
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

                        val withdrawBankDialog =
                            BottomSheetFinanceAddBank.newInstance(titleBank.toString(), imgBank)
                        withdrawBankDialog.show(
                            this@AddBankActivity.supportFragmentManager, "Deposit Dialog"
                        )
                    }
                }
            }

        }
}