package com.zillennium.utswap.module.finance.depositScreen.depositBottomSheet

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiDepositImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.databinding.BottomSheetFinanceDepositPaymentBinding
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.DepositOpenLinkWebViewActivity
import com.zillennium.utswap.utils.DecimalDigitsInputFilter
import com.zillennium.utswap.utils.groupingSeparator
import rx.Subscription


class BottomSheetFinanceDepositPayment: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener{

    private var binding: BottomSheetFinanceDepositPaymentBinding? = null
    var subscriptionOnDepositBalance: Subscription? = null
    var payment_method =""
    var coinname ="usd"
    var balance = ""
    var typeOfCard =""

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
            (dialog as BottomSheetDialog).behavior.state = STATE_EXPANDED
        }


//        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_finance_deposit_payment, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            nextBtnFinace.isEnabled = false
            nextBtnFinace.setOnClickListener {
                var bodyObj = DepositObj.DepositRequestBody()
                bodyObj.num = balance
                bodyObj.type = typeOfCard
                bodyObj.coinname = coinname
                bodyObj.payment_method = payment_method
                onDepositBalance(root.context, bodyObj)

//                if(!etMountPayment.text.isNullOrEmpty()){
//                    if(etMountPayment.text.toString().toLong() > 0){
//                        when(arguments?.getString("titleCard")){
//                            "ABA Pay" -> {
//                                intentOtherApp(UTSwapApp.instance, "com.paygo24.ibank", "C102577521")
//                            }
//                            "Acleda Bank" -> {
//                                intentOtherApp(UTSwapApp.instance, "com.domain.acledabankqr", "C103006903")
//                            }
//                            "Sathapana" -> {
//                                intentOtherApp(UTSwapApp.instance, "kh.com.sathapana.consumer", null)
//                            }
//                        }
////                        dismiss()
//                    }
//                }

            }

            arguments?.getInt("imgCard")?.let {
                imgCard.setImageResource(it)
            }
            arguments?.getString("titleCard").let {
                titleCard.text = it.toString()
                payment_method= it.toString()
            }
            arguments?.getString("typeOfCard").let {
                typeOfCard = it.toString()
            }

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



                    val amount: Double = if (!char.isNullOrEmpty()) { char.toString().toDouble()
                    } else {
                        '0'.toString().toDouble()
                    }

                    val fee = amount.toString().toDouble() * 0.01
                    val total = amount.toString().toDouble() + fee

                    tvAmount.text = "$${groupingSeparator(amount)}"
                    tvFee.text = "$${groupingSeparator(fee)}"
                    tvTotal.text = "$${groupingSeparator(total)}"
                    balance = total.toString()

                    nextBtnFinace.apply {
                        if(amount > 0){
                            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
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
        fun newInstance(cardTitle: String?,cardImg: String?, bic:String? ): BottomSheetFinanceDepositPayment {
            val depositBottomSheetDialog = BottomSheetFinanceDepositPayment()
            val args = Bundle()
            if (cardImg != null) {
                args.putString("imgCard", cardImg)
            }
            args.putString("titleCard", cardTitle)
            args.putString("typeOfCard",bic)
            depositBottomSheetDialog.arguments = args
            return depositBottomSheetDialog
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

     fun onDepositBalance(context: Context, body: DepositObj.DepositRequestBody) {
        subscriptionOnDepositBalance?.unsubscribe()
        subscriptionOnDepositBalance = ApiDepositImp().depositMoney(context, body).subscribe({
            if (it.status==1){
                DepositOpenLinkWebViewActivity.launchDepositOpenLinkWebViewActivity(context,it.data?.payment_link)
            }

        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {

                }
            }
        })
    }
}

