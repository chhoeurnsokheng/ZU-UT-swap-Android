package com.zillennium.utswap.screens.finance.depositScreen.depositBottomSheet

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetFinanceDepositPaymentBinding
import com.zillennium.utswap.utils.DecimalDigitsInputFilter
import com.zillennium.utswap.utils.groupingSeparator
import java.util.regex.Matcher
import java.util.regex.Pattern


class BottomSheetFinanceDepositPayment: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener{

    private var binding: BottomSheetFinanceDepositPaymentBinding? = null
    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_finance_deposit_payment, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))

            nextBtnFinace.isEnabled = false
            nextBtnFinace.setOnClickListener {
                if(!etMountPayment.text.isNullOrEmpty()){
                    if(etMountPayment.text.toString().toLong() > 0){
                        dismiss()
                    }
                }
            }

            arguments?.getInt("imgCard")?.let { imgCard.setImageResource(it.toInt()) }
            arguments?.getString("titleCard").let { titleCard.text = it.toString() }

            etMountPayment.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
            etMountPayment.requestFocus()
            etMountPayment.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {


                    val amount: Double = if (!char.isNullOrEmpty()) {
                        char.toString().toDouble()
                    } else {
                        '0'.toString().toDouble()
                    }

                    val fee = amount.toString().toDouble() * 0.01
                    val total = amount.toString().toDouble() + fee

                    tvAmount.text = "$${groupingSeparator(amount)}"
                    tvFee.text = "$${groupingSeparator(fee)}"
                    tvTotal.text = "$${groupingSeparator(total)}"


                    nextBtnFinace.apply {
                        if(amount > 0){
                            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                            isEnabled = true
                        }else{
                            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.dark_gray))
                            isEnabled = false
                        }
                    }


                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }
    }

    companion object{
        fun newInstance(cardTitle: String?,cardImg: Int?): BottomSheetFinanceDepositPayment {
            val depositBottomSheetDialog = BottomSheetFinanceDepositPayment()
            val args = Bundle()

            if (cardImg != null) {
                args.putInt("imgCard", cardImg)
            }
            args.putString("titleCard", cardTitle)

            depositBottomSheetDialog.arguments = args
            return depositBottomSheetDialog
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}

