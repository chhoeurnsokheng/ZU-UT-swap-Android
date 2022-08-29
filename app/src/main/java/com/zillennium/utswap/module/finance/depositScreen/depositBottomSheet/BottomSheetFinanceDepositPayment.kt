package com.zillennium.utswap.module.finance.depositScreen.depositBottomSheet

import android.content.Context
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.gson.Gson
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiDepositImp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.databinding.BottomSheetFinanceDepositPaymentBinding
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.DepositOpenLinkWebViewActivity
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.VerifyClientData
import com.zillennium.utswap.utils.groupingSeparator
import rx.Subscription
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

class BottomSheetFinanceDepositPayment : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetFinanceDepositPaymentBinding? = null
    var subscriptionOnDepositBalance: Subscription? = null
    var payment_method = ""
    var coinname = "usd"
    var balance = ""
    var fee = ""
    var typeOfCard = ""
    var image = ""
    var payment_link = ""
    var deep_link_url = ""
    var transitionId = ""
    var local_bank_fee = ""
    var visa_master_fee = " "
    private val df: DecimalFormat? = null
    private val dfnd: DecimalFormat? = null
    private val et: EditText? = null
    private val hasFractionalPart = false
    private var trailingZeroCount = 0
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

            nextBtnFinace.isEnabled = false
            nextBtnFinace.setOnClickListener {
                var params: Map<String, String> = emptyMap()
                params = mapOf(
                    "sign_type" to "MD5",
                    "type" to typeOfCard,
                    "num" to balance,
                    "coinname" to coinname,
                    "payment_method" to  payment_method,
                )
                val result = VerifyClientData.makeSign(params, SessionPreferences().SESSION_X_TOKEN_API.toString())

                var bodyObj = DepositObj.DepositRequestBody()
                bodyObj.num = balance
                bodyObj.type = typeOfCard
                bodyObj.coinname = coinname
                bodyObj.sign_type ="MD5"
                bodyObj.payment_method = payment_method
                bodyObj.sign = result
                onDepositBalance(root.context, bodyObj)








                binding?.progressBar?.visibility = View.VISIBLE
                val txtAmount = etMountPayment.text.toString().replace(",", "")
                var totalAmountValue = 0.0
                if (txtAmount.isNotEmpty()) {
                    totalAmountValue = txtAmount.toDouble()
                }

                if (totalAmountValue < 1) {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(
                        UTSwapApp.instance,
                        "Minimum deposit is $ 1.00",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    onDepositBalance(root.context, bodyObj)
                    binding?.progressBar?.visibility = View.VISIBLE
                }

            }

            arguments?.getString("imgCard")?.let {

                Glide.with(imgCard).load(it).fitCenter().into(imgCard)

            }
            arguments?.getString("payMentMethod").let {
                titleCard.text = it.toString()
                payment_method = "$it"
            }
            arguments?.getString("typeOfCard").let {
                typeOfCard = it.toString()
            }
            arguments?.getString("visa_master_fee").let {
                visa_master_fee = it.toString()
            }
            arguments?.getString("local_bank_fee").let {
                local_bank_fee = it.toString()
            }

            if (typeOfCard =="Visa/Master Card") {
                fee= visa_master_fee
            }else{
                fee = local_bank_fee
            }

            tvFee.text = fee
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


                    var amount = 0.0
                    if (!char.isNullOrEmpty() && !char.toString().startsWith(".")) {
                        amount = char.toString().toDouble()
                    }

                    if (binding?.etMountPayment?.getText()?.startsWith("0") == true || binding?.etMountPayment?.getText()?.startsWith(".") == true) {
                        binding?.etMountPayment?.setText("")
                    }

                    val total = amount.toString().toDouble() + fee.toDouble()

                    tvAmount.text = "$${groupingSeparator(amount)}"

                    tvTotal.text = "$${groupingSeparator(total)}"
                    balance = total.toString()


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

                    binding?.txtDorlla?.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                }

            })
        }
    }


    companion object {
        fun newInstance(
            paymentMethod: String?,
            cardImg: String?,
            type: String?,
            local_bank_fee:String?,
            visa_master_fee:String
        ): BottomSheetFinanceDepositPayment {
            val depositBottomSheetDialog = BottomSheetFinanceDepositPayment()
            val args = Bundle()
            if (cardImg != null) {
                args.putString("imgCard", cardImg)
                var image = cardImg
            }
            args.putString("payMentMethod", paymentMethod)
            args.putString("typeOfCard", type)
            args.putString("local_bank_fee", local_bank_fee)
            args.putString("visa_master_fee", visa_master_fee)


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
                    it.data?.payment_link,
                    it.data?.transaction_id
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

class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) :
    InputFilter {
    var mPattern: Pattern
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val matcher: Matcher = mPattern.matcher(dest)
        return if (!matcher.matches()) "" else null
    }

    init {
        mPattern =
            Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")
    }
}
