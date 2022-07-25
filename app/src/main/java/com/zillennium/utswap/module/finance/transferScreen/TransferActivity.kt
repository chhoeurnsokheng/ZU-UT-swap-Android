package com.zillennium.utswap.module.finance.transferScreen

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceTransferBinding
import com.zillennium.utswap.models.FinanceTransferModel
import com.zillennium.utswap.module.finance.transferScreen.adapter.TransferAdapter
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.DecimalDigitsInputFilter
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class TransferActivity :
    BaseMvpActivity<TransferView.View, TransferView.Presenter, ActivityFinanceTransferBinding>(),
    TransferView.View {

    override var mPresenter: TransferView.Presenter = TransferPresenter()
    override val layoutResource: Int = R.layout.activity_finance_transfer

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<FinanceTransferModel>
    lateinit var userProfile: Array<Int>
    lateinit var userName: Array<String>
    lateinit var userPhoneNumber: Array<String>
    private var transferAdapter: TransferAdapter? = null
    override fun initView() {
        super.initView()
        try {
            binding.apply {
                etMountTransfer.requestFocus()
                showKeyboard(this@TransferActivity)

                imgClose.setOnClickListener {
                    etMountTransfer.hideKeyboard()
                    finish()
                }
                layFragment.setOnClickListener {
                    etMountTransfer.hideKeyboard()
                }

                KeyboardVisibilityEvent.setEventListener(
                    this@TransferActivity,
                    object : KeyboardVisibilityEventListener {
                        override fun onVisibilityChanged(isOpen: Boolean) {
                            if (!isOpen) {
                                etMountTransfer.clearFocus()
                            }
                        }
                    })

                etMountTransfer.addTextChangedListener(textWatcher)
                etPhoneNumberScanQR.addTextChangedListener(textWatcher)
                nextBtnTransfer.isEnabled = false

                userProfile = arrayOf(
                    R.drawable.aba_pay,
                    R.drawable.acleda

                )

                userName = arrayOf(
                    "JONHATHAN WICK",
                    "ANTHONY EDWARD STARK"
                )

                userPhoneNumber = arrayOf(
                    "012 486 378",
                    "088 998 809"
                )

                newArrayList = arrayListOf<FinanceTransferModel>()
                for (i in userProfile.indices) {
                    val transfer = FinanceTransferModel(
                        userProfile[i],
                        userName[i],
                        userPhoneNumber[i]

                    )
                    newArrayList.add(transfer)
                }

                rvUserReceiving.layoutManager = LinearLayoutManager(UTSwapApp.instance)
//                transferAdapter = TransferAdapter(newArrayList)
//                rvUserReceiving.adapter = transferAdapter

                //Transfer Amount
                etMountTransfer.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
                etMountTransfer.addTextChangedListener(object : TextWatcher {
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
                        layTransactions.visibility = View.VISIBLE

                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })

                // Scan Phone Number
                etPhoneNumberScanQR.addTextChangedListener(object : TextWatcher {
                    var count = 0
                    private val EMPTY_STRING = ""
                    private val WHITE_SPACE = " "
                    private var lastSource = EMPTY_STRING
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
                        if (s?.toString()?.length!! >= 9) {
                            receiveUserPhoneNumber.visibility = View.VISIBLE
                            newArrayList[1].phoneNumber = s.toString()
                            transferAdapter = TransferAdapter(newArrayList)
                            rvUserReceiving.adapter = transferAdapter
                        } else {
                            receiveUserPhoneNumber.visibility = View.GONE
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                        var source = s.toString()
                        if (lastSource != source) {
                            source = source.replace(WHITE_SPACE, EMPTY_STRING)
                            val stringBuilder = StringBuilder()
                            for (i in source.indices) {
                                if (i > 0 && i % 3 == 0) {
                                    stringBuilder.append(WHITE_SPACE)
                                }
                                stringBuilder.append(source[i])
                            }
                            lastSource = stringBuilder.toString()
                            s!!.replace(0, s.length, lastSource)
                        }
                    }

                })


            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.apply {
                if (etMountTransfer.text.toString().isNotEmpty() && etPhoneNumberScanQR.text.toString().isNotEmpty()){
                    nextBtnTransfer.isEnabled = true
                    nextBtnTransfer.setOnClickListener{
                        val fundPasswordDialog: FundPasswordDialog = FundPasswordDialog()
                        fundPasswordDialog.show(this@TransferActivity.supportFragmentManager, "balanceHistoryDetailDialog")
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        binding.etMountTransfer.clearFocus()
    }

    private fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }
}