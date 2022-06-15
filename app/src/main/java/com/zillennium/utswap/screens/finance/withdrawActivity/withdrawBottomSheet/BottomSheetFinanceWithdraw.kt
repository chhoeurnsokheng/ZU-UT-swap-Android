package com.zillennium.utswap.screens.finance.withdrawActivity.withdrawBottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetFinanceWithdrawAmountBinding

class BottomSheetFinanceWithdraw : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private lateinit var bankAccount: LinearLayout
    private var REQUEST_CODE = 0
    private var binding: BottomSheetFinanceWithdrawAmountBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }



//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        binding?.apply {
//            if (requestCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
//                val value = intent.extras!!.getString("key")
//                if (value.toString() != "") {
//                    tvUser.text = value
//                    addBankAccount.visibility = View.GONE
//                    receiveAdd.visibility = View.VISIBLE
//                    abaUserInfo.visibility = View.VISIBLE
//                } else {
//                    super.onActivityResult(requestCode, resultCode, data)
//                }
//            }
//
//        }
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_finance_withdraw_amount,
            container,
            false
        )
        return binding?.root
    }


//    companion object{
//        fun newInstance(tvUser: String): BottomSheetFinanceWithdraw {
//            val withdrawBottomSheetDialog = BottomSheetFinanceWithdraw()
//            val args = Bundle()
//            args.putString("key", tvUser)
//
//            withdrawBottomSheetDialog.arguments = args
//            return withdrawBottomSheetDialog
//        }
//
//    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}