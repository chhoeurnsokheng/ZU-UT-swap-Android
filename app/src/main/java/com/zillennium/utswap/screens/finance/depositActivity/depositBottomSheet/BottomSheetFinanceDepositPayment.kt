package com.zillennium.utswap.screens.finance.depositActivity.depositBottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetFinanceDepositPaymentBinding
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.bottomSheet.SubscriptionBottomSheet
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.dialog.SubscriptionConfirmDialog
import com.zillennium.utswap.utils.groupingSeparator


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
            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            nextBtnFinace.setOnClickListener {
                if(!etMountPayment.text.isNullOrEmpty()){
                    if(etMountPayment.text.toString().toLong() > 0){
                        val subscriptionConfirmDialog: SubscriptionConfirmDialog = SubscriptionConfirmDialog.newInstance(etMountPayment.text.toString(),arguments?.get("title").toString())
                        subscriptionConfirmDialog.show(requireActivity().supportFragmentManager, "Finance Deposit Dialog")
                        dismiss()
                    }
                }
            }

            arguments?.getInt("imgCard")?.let { imgCard.setImageResource(it.toInt()) }
            arguments?.getString("titleCard").let { titleCard.setText(it.toString()) }

            etMountPayment.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val totalVolume = if(!etMountPayment.text.toString().isNullOrEmpty()){
                        etMountPayment.text.toString().toLong()
                    }else{
                        0
                    }

                    val name: String = etMountPayment.getText().toString()
                    tvAmount.setText(name);
                    val amount_total = (totalVolume * 2.00)
                    tvTotal.text = groupingSeparator(amount_total)
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
//            args.putString("imgTitle",cardTitle)

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