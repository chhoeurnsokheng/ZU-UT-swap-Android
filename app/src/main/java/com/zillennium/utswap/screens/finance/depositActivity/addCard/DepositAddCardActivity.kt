package com.zillennium.utswap.screens.finance.depositActivity.addCard

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


                btnAddCardBack.setOnClickListener {
//                    val intent = Intent(this@DepositAddCardActivity, DepositActivity::class.java)
//                    startActivity(intent)
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
                        txtShowMessage.visibility = View.GONE
                        etCardNumber.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.gray_999999
                            )
                        )
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
                        txtShowMessage.visibility = View.GONE
                        etDate.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.gray_999999
                            )
                        )

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
                        txtShowMessage.visibility = View.GONE
                        etCvv.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.gray_999999
                            )
                        )

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
                        txtShowMessage.visibility = View.GONE
                        etCardHolderName.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.gray_999999
                            )
                        )

                    }
                })

                btnAddCardConfirm.isEnabled = false
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
                inputCardholder = etCardHolderName.toString()
                btnAddCardConfirm.isEnabled =
                    inputCardNumber.isNotEmpty() && inputDate.isNotEmpty() && inputCvv.isNotEmpty() && inputCardholder.isNotEmpty()
                btnAddCardConfirm.setOnClickListener {
                    var isHaveError = false
                    if (inputCardNumber.length > 19) {
                        txtShowMessage.text = "Card number is invalid"
                        txtShowMessage.visibility = View.VISIBLE
                        etCardNumber.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.red
                            )
                        )
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (inputDate.isEmpty()) {
                        txtShowMessage.text = "Date is invalid"
                        txtShowMessage.visibility = View.VISIBLE
                        etDate.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.red
                            )
                        )
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (inputCvv.length > 3) {
                        txtShowMessage.text = "CVV is invalid"
                        txtShowMessage.visibility = View.VISIBLE
                        etCvv.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.red
                            )
                        )
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (inputCardholder.isEmpty()) {
                        txtShowMessage.text = "Cardholder Name is invalid"
                        txtShowMessage.visibility = View.VISIBLE
                        etCardHolderName.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.red
                            )
                        )
                        isHaveError = true
                        return@setOnClickListener
                    }

                    //use to check condition with DepositActivity to make bottomSheet popup on DepositActivity
                    val intent = Intent()
//                    intent.putExtra(Intent.EXTRA_TEXT, "Hello")
                    setResult(RESULT_OK, intent)
                    finish()

                }
            }
        }

        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable?) {
            var count = 0
            binding.apply {

                val inputNumberLength: Int = etCardNumber.text.toString().length

                if (count <= inputNumberLength && inputNumberLength == 4 ||
                    inputNumberLength == 9 || inputNumberLength == 14 || inputNumberLength == 19
                ) {

                    etCardNumber.setText(etCardNumber.text.toString() + " ")

                    val pos = etCardNumber.text.length
                    etCardNumber.setSelection(pos)

                } else if (count >= inputNumberLength && (inputNumberLength == 4 ||
                            inputNumberLength == 9 || inputNumberLength == 14 || inputNumberLength == 19)
                ) {
                    etCardNumber.setText(
                        etCardNumber.text.toString()
                            .substring(0, etCardNumber.text.toString().length - 1)
                    )

                    val pos = etCardNumber.text.length
                    etCardNumber.setSelection(pos)
                }
                count = etCardNumber.text.toString().length

            }
        }

    }
}


