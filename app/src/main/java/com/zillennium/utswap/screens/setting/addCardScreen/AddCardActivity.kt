package com.zillennium.utswap.screens.setting.addCardScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.vinaygaba.creditcardview.CardType
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingAddCardBinding
import java.io.IOException

class AddCardActivity :
    BaseMvpActivity<AddCardView.View, AddCardView.Presenter, ActivitySettingAddCardBinding>(),
    AddCardView.View {

    override var mPresenter: AddCardView.Presenter = AddCardPresenter()
    override val layoutResource: Int = R.layout.activity_setting_add_card

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                edtxtCardNumber.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        edCard.cardNumber = edtxtCardNumber.text.toString()
                        edCard.type = CardType.AUTO
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                edtxtCardName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        edCard.cardName = edtxtCardName.text.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                edtxtCardExpiry.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        edCard.expiryDate = edtxtCardExpiry.text.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                edtxtCardCvv.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        val txt_card_cvv = edtxtCardCvv.text.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }
}