package com.zillennium.utswap.module.finance.lockUpScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ActivityFinanceLockUpBinding
import com.zillennium.utswap.models.FinanceLockUpModel
import com.zillennium.utswap.module.finance.lockUpScreen.adapter.LockUpAdapter
import com.zillennium.utswap.module.finance.lockUpScreen.bottomSheet.FinanceLockUpBottomSheet
import com.zillennium.utswap.module.finance.lockUpScreen.dialog.FinanceLockUpDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FinanceLockUpActivity :
    BaseMvpActivity<FinanceLockUpView.View, FinanceLockUpView.Presenter, ActivityFinanceLockUpBinding>(),
    FinanceLockUpView.View {

    override var mPresenter: FinanceLockUpView.Presenter = FinanceLockUpPresenter()
    override val layoutResource: Int = R.layout.activity_finance_lock_up

    private var buyBackList = ArrayList<FinanceLockUpModel>()
    private var swapList = ArrayList<FinanceLockUpModel>()


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
                            buyBackList.add(FinanceLockUpModel("UT Kampot","02-May-2022", "02-June-2022", 400.00, "30 day(s) left", 0, "Buy Back"))

                            buyBackList.sortByDescending {
                                LocalDate.parse(
                                    it.titleLockUp,
                                    dateTimeFormatter
                                )
                            }

                            rvFilterLockUp.adapter = LockUpAdapter(buyBackList, onClickAdapterLockup)
                        }
                        "Swap" -> {
                            swapList.clear()
                            swapList.add(FinanceLockUpModel("House in Phnom Penh", "02-May-2022", "02-June-2022", 40000.00, "30 day(s) left",0, "Swap"))

                            swapList.sortByDescending {
                                LocalDate.parse(
                                    it.titleLockUp,
                                    dateTimeFormatter
                                )
                            }

                            rvFilterLockUp.adapter = LockUpAdapter(swapList, onClickAdapterLockup)
                        }
                    }
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
    private val onClickAdapterLockup: LockUpAdapter.OnClickAdapter = object : LockUpAdapter.OnClickAdapter {
        override fun onClickMe(financeLockUpModel: FinanceLockUpModel) {
            val financeLockUpDialog: FinanceLockUpDialog = FinanceLockUpDialog.newInstance(
                financeLockUpModel.titleLockUp,
                financeLockUpModel.amountLockUp,
                financeLockUpModel.dateStartLockUp,
                financeLockUpModel.dateEndLockUp,
                financeLockUpModel.durationLockUp,
                financeLockUpModel.category
            )
            financeLockUpDialog.show(supportFragmentManager, "Lock Up History")
        }
    }
}