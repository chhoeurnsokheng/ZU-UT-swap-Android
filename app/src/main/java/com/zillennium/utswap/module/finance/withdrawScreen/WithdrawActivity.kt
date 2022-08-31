package com.zillennium.utswap.module.finance.withdrawScreen

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceWithdrawBinding
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import com.zillennium.utswap.module.finance.withdrawScreen.addBank.AddBankActivity
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.DecimalDigitsInputFilter
import com.zillennium.utswap.utils.NoInternetLayoutUtil
import com.zillennium.utswap.utils.UtilKt
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.setEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class WithdrawActivity :
    BaseMvpActivity<WithdrawView.View, WithdrawView.Presenter, ActivityFinanceWithdrawBinding>(),
    WithdrawView.View {

    override var mPresenter: WithdrawView.Presenter = WithdrawPresenter()
    override val layoutResource: Int = com.zillennium.utswap.R.layout.activity_finance_withdraw
    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var isShow = false


    override fun initView() {
        super.initView()
        try {
            binding.apply {
                mPresenter.getUSerBalanceStatus(this@WithdrawActivity)
                etMountPayment.requestFocus()
                showKeyboard(this@WithdrawActivity)
                nextBtnFinace.isEnabled = false

                NoInternetLayoutUtil().noInternetLayoutUtil(binding.rlNoInt)


                setEventListener(this@WithdrawActivity, object : KeyboardVisibilityEventListener {
                    override fun onVisibilityChanged(isOpen: Boolean) {
                        if (!isOpen) {
                            etMountPayment.clearFocus()
                        }
                    }
                })

                addBankAccount.setOnClickListener {
                    etMountPayment.hideKeyboard()
                    val intent = Intent(this@WithdrawActivity, AddBankActivity::class.java)
                    startActivity(intent)
                }

                imgClose.setOnClickListener {
                    etMountPayment.hideKeyboard()
                    finish()
                }

                layFragment.setOnClickListener {
                    etMountPayment.hideKeyboard()
                }



                SessionVariable.SESSION_BANK.observe(this@WithdrawActivity) {
                    //Get data from bottom sheet add bank screen

                    val value = SessionVariable.SESSION_BANK.value?.get(0)
                    if (tvUser.toString() != "" && tvTitleBank.toString() != "") {
                        tvUser.text = value?.bank_fullname
                        tvTitleBank.text = value?.bank_name

                        when (value?.bank_name) {
                            "ABA Pay" -> {
                                ivBank.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        UTSwapApp.instance,
                                        com.zillennium.utswap.R.drawable.aba_bank
                                    )
                                )
                            }
                            "Acleda Bank" -> {
                                ivBank.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        UTSwapApp.instance,
                                        com.zillennium.utswap.R.drawable.acleda
                                    )
                                )
                            }
                            "Sathapana" -> {
                                ivBank.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        UTSwapApp.instance,
                                        com.zillennium.utswap.R.drawable.sathapana
                                    )
                                )
                            }
                        }

                        addBankAccount.visibility = View.GONE
                        receiveAdd.visibility = View.VISIBLE
                    }

                }

                etMountPayment.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
                etMountPayment.requestFocus()

                etMountPayment.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                        val amount: Double = if (!s.isNullOrEmpty()) {
                            s.toString().toDouble()
                        } else {
                            '0'.toString().toDouble()
                        }

                        if (amount > 0 && tvUser.text.isNotEmpty() && tvTitleBank.text.isNotEmpty()) {
                            nextBtnFinace.isEnabled = true
                            nextBtnFinace.setOnClickListener {
                                val fundPasswordDialog = FundPasswordDialog()
                                fundPasswordDialog.show(
                                    this@WithdrawActivity.supportFragmentManager,
                                    "balanceHistoryDetailDialog"
                                )
                            }
                        } else {
                            nextBtnFinace.isEnabled = false
                        }

                    }

                    override fun afterTextChanged(s: Editable?) {

                    }
                })

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun getUserBalanceStatusSuccess(data: BalanceFinance.GetUserBalanceInfo) {
        binding.apply {
            txtUserBalance.text = "$ " + data.data?.withdrawal_balance?.let {
                UtilKt().formatValue(
                    it.toDouble(),
                    "###,###.##"
                )
            }
            txtUserBalanceAvailable.text = "$ " + data.data?.available_balance?.let {
                UtilKt().formatValue(
                    it.toDouble(),
                    "###,###.##"
                )
            }

            txtUserBalancePandding.text =
                "$ " + data.data?.pending?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }

            txtUserBalanceLock.text =
                "$ " + data.data?.lock_up?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }

        }
    }

    override fun getUserBalanceStatusFail(data: String) {}

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        binding.etMountPayment.clearFocus()
    }

    private fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.apply {
            etMountPayment.clearFocus()
            etMountPayment.hideKeyboard()
        }
    }

}