package com.zillennium.utswap.module.finance.withdrawScreen.withdrawBottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetFinanceAddBankBinding
import com.zillennium.utswap.models.FinanceBankModel
import com.zillennium.utswap.utils.formatter.DecimalDigitsInputFilter

class BottomSheetFinanceAddBank : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    private var binding: BottomSheetFinanceAddBankBinding? = null
    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (dialog is BottomSheetDialog) {
            (dialog as BottomSheetDialog).behavior.skipCollapsed = true
            (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
//        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_finance_add_bank,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textChange()
        btnConfirm()

    }


    private fun textChange() {
        binding?.apply {

            etAccountNumber.addTextChangedListener(object : TextWatcher {
                var count = 0
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {

                    //Use to format space every 3 character of Bank Account Number
                    val inputNumberLength: Int = etAccountNumber.text.toString().length

                    if (count <= inputNumberLength && (inputNumberLength == 3 || inputNumberLength == 7 || inputNumberLength == 11)
                    ) {
                        etAccountNumber.setText(etAccountNumber.text.toString() + " ")
                        val pos = etAccountNumber.text.length
                        etAccountNumber.setSelection(pos)
                    } else if (count >= inputNumberLength && (inputNumberLength == 3 || inputNumberLength == 7 || inputNumberLength == 11)
                    ) {
                        etAccountNumber.setText(
                            etAccountNumber.text.toString()
                                .substring(0, etAccountNumber.text.toString().length - 1)
                        )
                        val pos = etAccountNumber.text.length
                        etAccountNumber.setSelection(pos)
                    }
                    count = etAccountNumber.text.toString().length

                }

            })
        }
    }

    private fun btnConfirm(){

        binding?.apply {
            (view?.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
            btnConfirm.setOnClickListener {
                val backData = ArrayList<FinanceBankModel>()
                backData.add(
                    FinanceBankModel(
                        arguments?.getString("titleCard").toString(),
                        txtUserName.text.toString(),
                        etAccountNumber.text.toString()
                    )
                )

                SessionVariable.SESSION_BANK.value = backData
                dismiss()
                activity?.onBackPressed()

            }

            arguments?.getInt("imgCard")?.let { imgCard.setImageResource(it.toInt()) }
            arguments?.getString("titleCard").let { titleCard.text = it.toString() }
            txtUserName.setText("Te Eangly")


        }
    }

    // ve image bank and title bank
    companion object {
        fun newInstance(titleBank: String, imageBank: Int?): BottomSheetFinanceAddBank {
            val withdrawBankBottomSheetDialog = BottomSheetFinanceAddBank()
            val args = Bundle()
            if (imageBank != null) {
                args.putInt("imgCard", imageBank)
            }
            args.putString("titleCard", titleBank)
            withdrawBankBottomSheetDialog.arguments = args
            return withdrawBankBottomSheetDialog
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}

