package com.zillennium.utswap.screens.finance.addCardScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceDepositAddCardBinding


class DepositAddCardActivity :
    BaseMvpActivity<DepositAddCardView.View, DepositAddCardView.Presenter, ActivityFinanceDepositAddCardBinding>(),
    DepositAddCardView.View {

    override var mPresenter: DepositAddCardView.Presenter = DepositAddCardPresenter()

    override val layoutResource: Int = R.layout.activity_finance_deposit_add_card
    private lateinit var inputCardNumber: String
    private lateinit var inputDate: String
    private lateinit var inputCvv: String
    private lateinit var inputCardholder: String

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun initView() {
        super.initView()
        try {

            binding.apply {

                // TextWatcher
                etCardNumber.addTextChangedListener(cardTextWatcher)
                etCvv.addTextChangedListener(cardTextWatcher)
                etDate.addTextChangedListener(cardTextWatcher)
                etCardHolderName.addTextChangedListener(cardTextWatcher)


                backImage.setOnClickListener {
                    onBackPressed()
//                    val intent = Intent(this@DepositAddCardActivity, DepositActivity::class.java)
//                    startActivity(intent)
                }

                btnAddCardConfirm.isEnabled = false
                btnAddCardConfirm.setOnClickListener {
                    finish()
                }



                etCardNumber.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        p2: Int,
                        count: Int
                    ) {
                        inputCardNumber = char.toString().trim()
//                        if (char != null) {
//                            if(p2 == 0 && (char.length == 4 || char.length == 9 || char.length == 16))
//                                etCardNumber.append(" ")
//                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }

                })
                etDate.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        char: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        inputDate = char.toString().trim()
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })

                etCvv.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        inputCvv = char.toString().trim()
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })
                etCardHolderName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {


                    }

                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        inputCardholder = char.toString().trim()
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }
                })


            }


        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val cardTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.apply {
                inputCardNumber = etCardNumber.text.toString().trim()
                inputDate = etDate.text.toString()
                inputCvv = etCvv.text.toString()
                inputCardholder = etCardHolderName.text.toString()

                btnAddCardConfirm.isEnabled = inputCardNumber.length >= 16 && inputDate.length == 5 && inputCvv.length == 3 && inputCardholder.isNotEmpty()
                println(btnAddCardConfirm.isEnabled)
            }
        }

        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable?) {

        }

    }
}


