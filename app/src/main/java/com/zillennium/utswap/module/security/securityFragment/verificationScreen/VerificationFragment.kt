package com.zillennium.utswap.module.security.securityFragment.verificationScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityVerificationBinding
import com.zillennium.utswap.data.api.model.userService.User
import com.zillennium.utswap.module.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VerificationFragment :
    BaseMvpFragment<VerificationView.View, VerificationView.Presenter, FragmentSecurityVerificationBinding>(),
    VerificationView.View {

    override var mPresenter: VerificationView.Presenter = VerificationPresenter()
    override val layoutResource: Int = R.layout.fragment_security_verification

    private var countDownTimer: CountDownTimer? = null
    private val timeLeftInMilliseconds: Long = 120000 //120 second = 120000

    override fun initView() {
        super.initView()
        startTimer()
        onOrderActivity()
        onSetTitle(arguments?.getString("title").toString())
        onEditTextChange()
        onResendCode()
        onSubmitVerifyCode()
    }

    private fun onOrderActivity(){
        binding.apply {
            editBox.requestFocus()
            if (isAdded) {
                showKeyboard(requireContext())
            }

            imgBack.setOnClickListener {
                fragmentManager?.backStackEntryCount
                findNavController().popBackStack()

            }

            imgWrong.setOnClickListener {
                onBoxesBackgroundColor()
                editBox.setText("")
                layoutCount.performClick()
            }

            layoutCount.setOnClickListener {
                onBoxesBackgroundColor()

                if(editBox.text.isEmpty()){
                    val textInputLast = layoutCount.getChildAt(0) as TextView
                    textInputLast.text = "|"
                }

                editBox.requestFocus()
                val inputManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(editBox, InputMethodManager.SHOW_IMPLICIT)

            }

        }
    }

    private fun onSetTitle(title: String){
        binding.apply {
            when (title) {
                Constants.FundPassword.ChangeLoginPassword ->{
                    txtTitle.text = resources.getString(R.string.change_login_password)
                    txtTitle.visibility = View.VISIBLE
                }
                Constants.FundPassword.ChangeFundPassword -> {
                    txtTitle.text = resources.getString(R.string.change_fund_password)
                    txtTitle.visibility = View.VISIBLE
                }
                Constants.FundPassword.ForgotLoginPassword->{
                    txtTitle.text = resources.getString(R.string.forgot_login_password)
                    txtTitle.visibility = View.VISIBLE
                }
                Constants.FundPassword.forgotFundPassword -> {
                    txtTitle.text = resources.getString(R.string.change_fund_password)
                    txtTitle.visibility = View.VISIBLE
                }
                Constants.FundPassword.ForgotFundPassword -> {
                    txtTitle.text = resources.getString(R.string.forgot_fund_password)
                    txtTitle.visibility = View.VISIBLE
                }
                Constants.FundPassword.AddNumber -> {
                    txtTitle.text = resources.getString(R.string.account)
                    txtTitle.visibility = View.VISIBLE
                }
                Constants.FundPassword.Register -> {
                    btnNext.visibility = View.GONE

                }
                else -> {
                    txtTitle.visibility = View.GONE
                }
            }

        }
    }

    private fun onEditTextChange(){
        binding.apply {
            editBox.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    editBox.requestFocus()
                    imgCorrect.visibility = View.GONE
                    for (child in layoutCount.children){
                        val children = child as TextView
                        children.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_corner)
                        children.text = ""

                    }

                    if(chr?.length!! <= 5){
                        val textInputLast = chr.length.let { layoutCount.getChildAt(it.toInt()) } as TextView
                        textInputLast.text = "|"
                    }

                    for (index in chr.indices) {
                        val textInput = layoutCount.getChildAt(index) as TextView
                        textInput.text = chr[index].toString()

                        if(index == layoutCount.childCount - 1){
                            /* val inputManager =
                                 requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                             inputManager.hideSoftInputFromWindow(view?.windowToken, 0)*/
                        }
                    }
                }


                override fun afterTextChanged(p0: Editable?) {
                    if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty()){
                        imgWrong.visibility = View.GONE
                        linearCountdown.visibility = View.GONE
                        stopTimer()
                        lifecycleScope.launch {
                            delay(1000)
                            if (arguments?.getString("title") == "register") {
                                findNavController().navigate(R.id.action_to_term_condition_security_fragment)
                                editBox.setText("")
                                hideKeyboard()
                            }
                        }
                    } else if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty() ){
                        onBoxesBackgroundColor(1)
                        stopTimer()
                    }
                }

            })

        }
    }

    private fun onSubmitVerifyCode(){
        binding.apply {
            btnNext.setOnClickListener {
                if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty()) {

                    onProgressBar(true)
                    mPresenter.otpVerification(User.OtpObject(editBox.text.toString(),SessionPreferences().SESSION_SECURE_KEY.toString()),UTSwapApp.instance)

                } else {
                    onBoxesBackgroundColor(1)
                }
                hideKeyboard()
            }
        }
    }

    private fun onResendCode(){
        binding.apply {
            resendCode.setOnClickListener {
                stopTimer()
                startTimer()
                btnNext.isEnabled = true
                Toast.makeText(
                    requireActivity(),
                    "New code has been sent to your email!",
                    Toast.LENGTH_SHORT
                ).show()
                editBox.setText("")
                onBoxesBackgroundColor()
                layoutCount.performClick()
            }
        }
    }

    override fun otpSuccess(body: User.OtpRes) {
        binding.apply {

            onBoxesBackgroundColor(2)
            onProgressBar(false)

            when (arguments?.getString("title")) {
                Constants.FundPassword.SignIn -> {

                    SessionPreferences().SESSION_STATUS = true
                    SessionPreferences().SESSION_TOKEN = body.data?.TOKEN.toString()
                    SessionPreferences().SESSION_ID = body.data?.ID.toString()
                    SessionPreferences().SESSION_X_TOKEN_API = body.data?.x_api_key.toString()

                    activity?.finish()
                }
                Constants.FundPassword.ResetPassword -> {
                    findNavController().navigate(R.id.action_to_new_password_security_fragment)
                }
                Constants.FundPassword.Register -> {
                    findNavController().navigate(R.id.action_to_term_condition_security_fragment)
                }
                Constants.FundPassword.ChangeLoginPassword->{
                    activity?.finish()
                }
                Constants.FundPassword.forgotFundPassword -> {
                    activity?.finish()
                }
                Constants.FundPassword.ForgotFundPassword -> {
                    val bundle = Bundle()
                    bundle.putString("title", "Finish")
                    findNavController().navigate(R.id.action_from_verify_to_new_fund_password,bundle)
                }
                Constants.FundPassword.ForgotLoginPassword-> {
                    findNavController().navigate(R.id.action_to_new_account_login_password)
                }
                Constants.FundPassword.AddNumber -> {
                    activity?.finish()
                }
            }
        }
    }

    override fun otpFail(body: User.OtpRes) {
        binding.apply {
            onBoxesBackgroundColor(1)
            onProgressBar(false)
        }
    }

    private fun onBoxesBackgroundColor(status: Int = 0){
        //status: 0 normal , 1 fail, 2 success
        binding.apply {
            when(status){
                1 -> {
                    layoutCount.isEnabled = false
                    linearCountdown.visibility = View.GONE
                    imgCorrect.visibility = View.GONE
                    imgWrong.visibility = View.VISIBLE

                    for(child in layoutCount.children){
                        child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_red_corner)
                    }
                }
                2 -> {
                    imgCorrect.visibility = View.VISIBLE
                    for(child in layoutCount.children){
                        child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_green_corner)
                    }
                }
                else -> {
                    layoutCount.isEnabled = true

                    linearCountdown.visibility = View.VISIBLE
                    imgCorrect.visibility = View.GONE
                    imgWrong.visibility = View.GONE

                    for(child in layoutCount.children){
                        child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_corner)
                    }
                }
            }
        }
    }

    private fun onProgressBar(status: Boolean){
        binding.apply {
            if(status){
                pbNext.visibility = View.VISIBLE
                btnNext.isClickable = false
                btnNext.alpha = 0.6F
            }else{
                pbNext.visibility = View.GONE
                btnNext.isClickable = true
                btnNext.alpha = 1F
            }
        }
    }

    private fun startTimer() {
        binding.apply {
            stopTimer()
            countDownTimer = object : CountDownTimer(timeLeftInMilliseconds, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(l: Long) {
                    txtCountDown.text = "" + l / 1000
                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onFinish() {
                    btnNext.isEnabled = false
                    activity?.apply {
                        Toast.makeText(
                            this,
                            "You're run out of time!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    editBox.setText("")
                    onBoxesBackgroundColor(1)
                }
            }.start()
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.getString("title") == "register") {
            if ((activity as RegisterActivity).fromVerify) {
                (activity as RegisterActivity).fromVerify = false
                findNavController().popBackStack()
            }
        }
    }

    private fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}
