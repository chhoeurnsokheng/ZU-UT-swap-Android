package com.zillennium.utswap.module.finance.depositScreen.depositBottomSheet

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.gson.Gson
import com.zillennium.utswap.BuildConfig
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


class BottomSheetFinanceDepositPayment : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetFinanceDepositPaymentBinding? = null
    var subscriptionOnDepositBalance: Subscription? = null
    var payment_method = ""
    var coinname = "usd"
    var balance = ""
    var fee = ""
    var typeOfCard = ""
    var payment_link = ""
    var deep_link_url = ""
    var transitionId= ""
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

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_finance_deposit_payment,
            container,
            false
        )
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseAnalytics.getInstance(requireActivity())
        binding?.apply {
            var intent = Intent()
             fireBase()
            nextBtnFinace.isEnabled = false
            nextBtnFinace.setOnClickListener {
                val data = Gson().toJson("TR2208-1661140965780133")
                var bodyObj = DepositObj.DepositRequestBody()
                bodyObj.num = balance
                bodyObj.type = typeOfCard
                bodyObj.coinname = coinname
                bodyObj.deep_link = ""     // deep_link_url
                bodyObj.payment_method = payment_method

                onDepositBalance(root.context, bodyObj)
                binding?.progressBar?.visibility = View.VISIBLE
            }

            arguments?.getInt("imgCard")?.let {
                imgCard.setImageResource(it)

            }
            arguments?.getString("payMentMethod").let {
                titleCard.text = it.toString()
                payment_method = "$it"
            }
            arguments?.getString("typeOfCard").let {
                typeOfCard = it.toString()
            }

            etMountPayment.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
            etMountPayment.requestFocus()

            etMountPayment.addTextChangedListener(object : TextWatcher {
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


                    val amount: Double = if (!char.isNullOrEmpty()) {
                        char.toString().toDouble()
                    } else {
                        '0'.toString().toDouble()
                    }

                    val txtFeeValue = amount.toString().toDouble() * 0.01
                    val total = amount.toString().toDouble() + txtFeeValue

                    tvAmount.text = "$${groupingSeparator(amount)}"
                    tvFee.text = "$${groupingSeparator(txtFeeValue)}"
                    tvTotal.text = "$${groupingSeparator(total)}"
                    balance = total.toString()
                    fee = txtFeeValue.toString()

                    nextBtnFinace.apply {
                        if (amount > 0) {
                            backgroundTintList = ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.primary
                                )
                            )
                            isEnabled = true
                        } else {
                            backgroundTintList = ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.dark_gray
                                )
                            )
                            isEnabled = false
                        }
                    }


                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }
    }

    companion object {
        fun newInstance(
            paymentMethod: String?,
            cardImg: String?,
            type: String?
        ): BottomSheetFinanceDepositPayment {
            val depositBottomSheetDialog = BottomSheetFinanceDepositPayment()
            val args = Bundle()
            if (cardImg != null) {
                args.putString("imgCard", cardImg)
            }
            args.putString("payMentMethod", paymentMethod)
            args.putString("typeOfCard", type)


            depositBottomSheetDialog.arguments = args
            return depositBottomSheetDialog
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun fireBase() {

        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse("http://m.utswaptranding.com"))
            .setSocialMetaTagParameters(
                DynamicLink.SocialMetaTagParameters.Builder()
                    .setTitle("Hello sokheng")
                    .setImageUrl(Uri.parse(R.drawable.aba_pay.toString()))
                    .build()
            )
            .setDomainUriPrefix(BuildConfig.FIRE_BASE_URL) // Open links with this app on Android
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder()  //.setFallbackUrl(Uri.parse("https://www.youtube.com/watch?v=7aekxC_monc&list=RDVdQHUbv0rMM&index=8"))
                .build()
            )
            .setIosParameters(
                DynamicLink.IosParameters.Builder("com.utswapapp.ios")
                    .setFallbackUrl(Uri.parse("https://apps.apple.com/us/app/utswapapp-app/id1518963601"))
                    .build()
            )
            .buildShortDynamicLink()
            .addOnCompleteListener { task ->
                deep_link_url = task.result.shortLink.toString()
            }
            .addOnFailureListener {
                Log.e("ShareFail", it.message.toString())
            }
    }

    fun onDepositBalance(context: Context, body: DepositObj.DepositRequestBody) {
        subscriptionOnDepositBalance?.unsubscribe()
        subscriptionOnDepositBalance = ApiDepositImp().depositMoney(context, body).subscribe({
            transitionId = it.data?.transaction_id.toString()
            if (it.data?.payment_link != null) {
                binding?.progressBar?.visibility = View.GONE
                DepositOpenLinkWebViewActivity.launchDepositOpenLinkWebViewActivity(
                    context,
                    it.data?.payment_link
                )
            }

        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {

                }
            }
        })
    }
}

