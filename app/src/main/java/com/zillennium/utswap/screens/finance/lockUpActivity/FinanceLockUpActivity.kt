package com.zillennium.utswap.screens.finance.lockUpActivity

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ActivityFinanceLockUpBinding
import com.zillennium.utswap.models.FinanceBalanceModel
import com.zillennium.utswap.models.financeLockUp.BuyBackModel
import com.zillennium.utswap.models.financeLockUp.SwapModel
import com.zillennium.utswap.models.portfolio.Change
import com.zillennium.utswap.models.portfolio.Price
import com.zillennium.utswap.screens.finance.balanceActivity.adapter.FinanceBalanceAdapter
import com.zillennium.utswap.screens.finance.balanceActivity.dialog.FinanceBalanceDialog
import com.zillennium.utswap.screens.finance.lockUpActivity.adapter.BuyBackAdapter
import com.zillennium.utswap.screens.finance.lockUpActivity.adapter.SwapAdapter
import com.zillennium.utswap.screens.finance.lockUpActivity.bottomSheet.FinanceLockUpBottomSheet
import com.zillennium.utswap.screens.finance.lockUpActivity.dialog.FinanceLockUpBuyBackDialog
import com.zillennium.utswap.screens.finance.lockUpActivity.dialog.FinanceLockUpSwapDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FinanceLockUpActivity :
    BaseMvpActivity<FinanceLockUpView.View, FinanceLockUpView.Presenter, ActivityFinanceLockUpBinding>(),
    FinanceLockUpView.View {

    override var mPresenter: FinanceLockUpView.Presenter = FinanceLockUpPresenter()
    override val layoutResource: Int = R.layout.activity_finance_lock_up

    private var buyBackList = ArrayList<BuyBackModel>()
    private var swapList = ArrayList<SwapModel>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                backImage.setOnClickListener {
                    finish()
                }

                layFilter.setOnClickListener {
                    val financeLockUpBottomSheet: FinanceLockUpBottomSheet =
                        FinanceLockUpBottomSheet.newInstance(btnFilter.text.toString())
                    financeLockUpBottomSheet.show(
                        supportFragmentManager, "Filter Lock Up"
                    )
                }

                val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")

                rvFilterLockUp.layoutManager = LinearLayoutManager(UTSwapApp.instance)

                SettingVariable.finance_lock_up_selected.observe(this@FinanceLockUpActivity){
                    btnFilter.text = SettingVariable.finance_lock_up_selected.value.toString()

                    when(SettingVariable.finance_lock_up_selected.value.toString()) {
                        "Buy Back" -> {
                            buyBackList.clear()
                            buyBackList.add(BuyBackModel("UT Kampot","02-May-2022", "02-June-2022", 400.00, "30 day(s) left", 0))

                            buyBackList.sortByDescending {
                                LocalDate.parse(
                                    it.titleLockUp,
                                    dateTimeFormatter
                                )
                            }

                            rvFilterLockUp.adapter = BuyBackAdapter(buyBackList, onClickAdapterBuyBack)
                        }
                        "Swap" -> {
                            swapList.clear()
                            swapList.add(SwapModel("House in Phnom Penh", "02-May-2022", "02-June-2022", 40000.00, "30 day(s) left",0))

                            swapList.sortByDescending {
                                LocalDate.parse(
                                    it.titleLockUp,
                                    dateTimeFormatter
                                )
                            }

                            rvFilterLockUp.adapter = SwapAdapter(swapList, onClickAdapterSwap)
                        }
                    }
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
    private val onClickAdapterBuyBack: BuyBackAdapter.OnClickAdapter = object : BuyBackAdapter.OnClickAdapter {
        override fun onClickMe(buyBackModel: BuyBackModel) {
            val financeLockUpBuyBackDialog: FinanceLockUpBuyBackDialog = FinanceLockUpBuyBackDialog.newInstance(
                buyBackModel.titleLockUp,
                buyBackModel.amountLockUp,
                buyBackModel.dateStartLockUp,
                buyBackModel.dateEndLockUp,
                buyBackModel.durationLockUp
            )
            financeLockUpBuyBackDialog.show(supportFragmentManager, "Buy Back History")
        }
    }

    private val onClickAdapterSwap: SwapAdapter.OnClickAdapter = object : SwapAdapter.OnClickAdapter {
        override fun onClickMe(swapModel: SwapModel) {
            val financeLockUpSwapDialog: FinanceLockUpSwapDialog = FinanceLockUpSwapDialog.newInstance(
                swapModel.titleLockUp,
                swapModel.amountLockUp,
                swapModel.dateStartLockUp,
                swapModel.dateEndLockUp,
                swapModel.durationLockUp
            )
            financeLockUpSwapDialog.show(supportFragmentManager, "Swap History")
        }

    }
}