package com.zillennium.utswap.module.finance.transferScreen

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceTransferBinding
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import com.zillennium.utswap.models.financeTransfer.Transfer
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.account.addNumberScreen.AddNumberActivity
import com.zillennium.utswap.module.finance.transferScreen.dialog.TransferSuccessDialog
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.DecimalDigitsInputFilter
import com.zillennium.utswap.utils.UtilKt
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class TransferActivity :
    BaseMvpActivity<TransferView.View, TransferView.Presenter, ActivityFinanceTransferBinding>(),
    TransferView.View {

    override var mPresenter: TransferView.Presenter = TransferPresenter()
    override val layoutResource: Int = R.layout.activity_finance_transfer

    private val blockCharacterSet = "~#^|$%&*!,./()-+;N"

    private var amountTransfer = ""
    private var currencyTransfer = "usd"
    private var receiverTransfer = ""

    private var countLoop = 0


    override fun initView() {
        super.initView()
        try {
            binding.apply {

                onCallApi()
                onTextChangeListener()

                SessionVariable.successTransfer.value = false

                SessionVariable.successTransfer.observe(this@TransferActivity){
                    if(it){
                        etPhoneNumberScanQR.setText("0")
                        etMountTransfer.setText("")
                        layTransactions.visibility = View.GONE
                        etMountTransfer.hideKeyboard()

                        val transferSuccessDialog = TransferSuccessDialog()
                        transferSuccessDialog.show(supportFragmentManager, "Transfer Success Dialog")

                        mPresenter.onGetUserInfo(UTSwapApp.instance)
                    }
                }

                etPhoneNumberScanQR.setText("0")

                layAddPhoneNumber.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, AddNumberActivity::class.java)
                    startActivity(intent)
                }

                etPhoneNumberScanQR.filters = arrayOf(filter, LengthFilter(12))

                imgClose.setOnClickListener {
                    etMountTransfer.hideKeyboard()
                    layTransactions.visibility = View.GONE
                    finish()
                }
                imgCloseNoPhoneNumber.setOnClickListener{
                    etMountTransfer.hideKeyboard()
                    layTransactions.visibility = View.GONE
                    finish()
                }
                layFragmentTransfer.setOnClickListener {
                    etMountTransfer.hideKeyboard()
                    etPhoneNumberScanQR.hideKeyboard()
                    layTransactions.visibility = View.GONE
                    etPhoneNumberScanQR.clearFocus()
                }

                etMountTransfer.addTextChangedListener(textWatcher)
                etPhoneNumberScanQR.addTextChangedListener(textWatcher)
                nextBtnTransfer.isEnabled = false

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected) {
                mPresenter.onGetUserInfo(UTSwapApp.instance)
                binding.progressBar.visibility = View.GONE
                binding.layNoInternet.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.VISIBLE
                binding.layNoInternet.visibility = View.VISIBLE
            }
        }
    }

    override fun onGetUserInfoSuccess(data: User.AppSideBarData) {
        binding.apply {

            if (data.phonenumber!!.isNotEmpty()){
                layNoPhoneNumber.visibility = View.GONE
                layNoPhoneNumberTransparent.visibility = View.GONE
                mPresenter.onGetUserBalanceInfo(UTSwapApp.instance)
                if (SessionVariable.successTransfer.value == false){
                    onRequestKeyBoard()
                }
            }else{
                layNoPhoneNumber.visibility = View.VISIBLE
                layNoPhoneNumberTransparent.visibility = View.VISIBLE
            }
        }
    }
    override fun onGetUserInfoFail(data: User.AppSideBarData) {}
    override fun onGetUserBalanceInfoSuccess(data: BalanceFinance.GetUserBalanceInfoData) {
        binding.apply {
            txtTransferBalance.text = "$ " + data.transfer_balance?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
            txtAvailableBalance.text = "$ " + data.available_balance?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
            txtPending.text = "$ " + data.pending?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
            txtLockUp.text = "$ " + data.lock_up?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
        }
    }
    override fun onGetUserBalanceInfoFail(data: BalanceFinance.GetUserBalanceInfo) {}
    override fun onGetValidateTransferSuccess(data: Transfer.GetValidateTransferData) {
        binding.apply {

            loadingTransfer.visibility = View.GONE
            val fundPasswordDialog: FundPasswordDialog = FundPasswordDialog.transferInstance(amountTransfer, currencyTransfer, receiverTransfer, Constants.TransferFundPassword.transfer)
            fundPasswordDialog.show(supportFragmentManager, "Fund Password Dialog Transfer")
        }
    }
    override fun onGetValidateTransferFail(data: Transfer.GetValidateTransfer) {
        binding.apply {
            loadingTransfer.visibility = View.GONE
            Toast.makeText(UTSwapApp.instance, data.message, Toast.LENGTH_LONG).show()

        }
    }

    private val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source != null && blockCharacterSet.contains("" + source)) {
            ""
        } else null
    }
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.apply {
                if (etMountTransfer.text.toString()
                        .isNotEmpty() && etPhoneNumberScanQR.text.length >= 11
                ) {
                    nextBtnTransfer.isEnabled = true
                    nextBtnTransfer.setOnClickListener {
                        amountTransfer = etMountTransfer.text.toString()
                        receiverTransfer = etPhoneNumberScanQR.text.toString().replace(" ", "")
                        etMountTransfer.hideKeyboard()
                        etPhoneNumberScanQR.hideKeyboard()
                        etMountTransfer.clearFocus()
                        etPhoneNumberScanQR.clearFocus()
                        layTransactions.visibility = View.GONE
                        loadingTransfer.visibility = View.VISIBLE
                        Handler().postDelayed({
                            mPresenter.onGetValidateTransfer(Transfer.GetValidateTransferObject(amountTransfer, currencyTransfer, receiverTransfer), UTSwapApp.instance)
                        }, 1000)
                    }
                } else {
                    nextBtnTransfer.isEnabled = false
                }
            }
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun onTextChangeListener(){
        binding.apply {
            //Transfer Amount and Receive Number
            etMountTransfer.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
            etMountTransfer.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (etMountTransfer.text.toString().isEmpty()){
                        dollarSymbol.setTextColor(Color.parseColor("#CCCCCC"))
                    }else{
                        dollarSymbol.setTextColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.white))
                    }
                    if (etMountTransfer.text.toString().startsWith(".") || etMountTransfer.text.toString().startsWith("0"))
                        etMountTransfer.setText(etMountTransfer.text.toString().substring(1))

                }

                override fun afterTextChanged(s: Editable?) {}

            })
            etPhoneNumberScanQR.addTextChangedListener(object : TextWatcher {
                private val EMPTY_STRING = ""
                private val WHITE_SPACE = " "
                private var lastSource = EMPTY_STRING

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (s.toString().matches("^00".toRegex())) {
                        etPhoneNumberScanQR.removeTextChangedListener(this)
                        etPhoneNumberScanQR.setText("0")
                        etPhoneNumberScanQR.addTextChangedListener(this)
                        etPhoneNumberScanQR.setSelection(s?.length?.minus(1) ?: 0)
                    }
                    if (etPhoneNumberScanQR.length() == 0) {
                        etPhoneNumberScanQR.setText("0")
                        etPhoneNumberScanQR.setSelection(s?.length?.plus(1) ?: 0)
                    }

                    if (s?.toString()?.length!! >= 11 && etMountTransfer.text.toString().isNotEmpty()
                    ) {
                        nextBtnTransfer.isEnabled = true
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    var source = s.toString()
                    if (lastSource != source) {
                        source = source.replace(WHITE_SPACE, EMPTY_STRING)
                        val stringBuilder = StringBuilder()
                        countLoop++
                        for (i in source.indices) {
                            if (i > 0 && i % 3 == 0 && i != 9) {
                                stringBuilder.append(WHITE_SPACE)
                            }
                            stringBuilder.append(source[i])
                        }
                        lastSource = stringBuilder.toString()
                        s?.replace(0, s.length, lastSource)
                    }
                }
            })
        }
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        binding.etMountTransfer.clearFocus()
    }
    private fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }
    private fun onRequestKeyBoard(){
        binding.apply {
            etMountTransfer.requestFocus()

            showKeyboard(this@TransferActivity)

            KeyboardVisibilityEvent.setEventListener(
                this@TransferActivity,
                object : KeyboardVisibilityEventListener {
                    override fun onVisibilityChanged(isOpen: Boolean) {
                        if (!isOpen) {
                            etMountTransfer.clearFocus()
                        }else{
                            layTransactions.visibility = View.VISIBLE
                        }
                    }
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SessionVariable.successTransfer.value = false
    }
    override fun onBackPressed() {
        super.onBackPressed()
        SessionVariable.successTransfer.value = false
    }

}