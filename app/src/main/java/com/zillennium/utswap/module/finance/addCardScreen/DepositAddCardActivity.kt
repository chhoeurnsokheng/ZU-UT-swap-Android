package com.zillennium.utswap.module.finance.addCardScreen

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
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
        toolBar()
        try {

            binding.apply {

                // TextWatcher
                etCardNumber.addTextChangedListener(cardTextWatcher)
                etCvv.addTextChangedListener(cardTextWatcher)
                etDate.addTextChangedListener(cardTextWatcher)
                etCardHolderName.addTextChangedListener(cardTextWatcher)

                btnAddCardConfirm.isEnabled = false
                btnAddCardConfirm.setOnClickListener {
                    finish()
                }



                etCardNumber.addTextChangedListener(object : TextWatcher {
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
                        char: CharSequence?,
                        start: Int,
                        p2: Int,
                        count: Int
                    ) {
                        inputCardNumber = char.toString().trim()

                    }

                    override fun afterTextChanged(s: Editable?) {

                        //Use to format edittext to display spaces after every 4 characters of Card Number
                        val inputlength: Int = etCardNumber.text.toString().length;

                        if (count <= inputlength && (inputlength == 4 ||
                                    inputlength == 9 || inputlength == 14 || inputlength == 19)
                        ) {
                            etCardNumber.setText(etCardNumber.text.toString() + " ");
                            val pos = etCardNumber.text.length;
                            etCardNumber.setSelection(pos);
                        } else if (count >= inputlength && (inputlength == 4 ||
                                    inputlength == 9 || inputlength == 14 || inputlength == 19)
                        ) {
                            etCardNumber.setText(
                                etCardNumber.text.toString()
                                    .substring(0, etCardNumber.text.toString().length - 1)
                            );
                            val pos = etCardNumber.text.length
                            etCardNumber.setSelection(pos);
                        }
                        count = etCardNumber.text.toString().length

//                        var source = s.toString()
//                        if (lastSource != source) {
//                            source = source.replace(WHITE_SPACE, EMPTY_STRING)
//                            val stringBuilder = StringBuilder()
//                            for (i in source.indices) {
//                                if (i > 0 && i % 4 == 0) {
//                                    stringBuilder.append(WHITE_SPACE)
//                                }
//                                stringBuilder.append(source[i])
//                            }
//                            lastSource = stringBuilder.toString()
//                            s!!.replace(0, s.length, lastSource)
//                        }
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

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.add_visa_or_master_card)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
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

                btnAddCardConfirm.isEnabled =
                    inputCardNumber.length >= 16 && inputDate.length == 5 && inputCvv.length == 3 && inputCardholder.isNotEmpty()

            }
        }

        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable?) {

        }

    }

}


