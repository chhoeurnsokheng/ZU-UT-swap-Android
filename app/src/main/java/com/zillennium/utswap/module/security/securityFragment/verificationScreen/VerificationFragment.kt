package com.zillennium.utswap.module.security.securityFragment.verificationScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
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
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityVerificationBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VerificationFragment :
    BaseMvpFragment<VerificationView.View, VerificationView.Presenter, FragmentSecurityVerificationBinding>(),
    VerificationView.View {

    override var mPresenter: VerificationView.Presenter = VerificationPresenter()
    override val layoutResource: Int = R.layout.fragment_security_verification

    private var countDownTimer: CountDownTimer? = null
    private val timeLeftInMilliseconds: Long = 120000 //120 second = 120000

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {
                editBox.requestFocus()
//                activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

                imgBack.setOnClickListener {
                    fragmentManager?.backStackEntryCount
                    findNavController().popBackStack()

                }

                when (arguments?.getString("title")) {
                    "change login password"->{
                        title.text = "Change Login Password"
                        title.visibility = View.VISIBLE
                    }
                    "Change Fund Password" -> {
                        title.text = "Change Fund Password"
                        title.visibility = View.VISIBLE
                    }
                    "Forgot Login Password"->{
                        title.text = "Forgot Login Password"
                        title.visibility = View.VISIBLE
                    }
                    "forgot fund password" -> {
                        title.text = "Change Fund Password"
                        title.visibility = View.VISIBLE
                    }
                    "Forgot Fund Password" -> {
                        title.text = "Forgot Fund Password"
                        title.visibility = View.VISIBLE
                    }
                    "add number" -> {
                        title.text = "Account"
                        title.visibility = View.VISIBLE
                    }
                    else -> {
                        title.visibility = View.GONE
                        btnNext.visibility = View.GONE
                    }
                }



                startTimer()

                layoutCount.setOnClickListener {
                    linearCountdown.visibility = View.VISIBLE
                    imgWrong.visibility = View.GONE
                    imgCorrect.visibility = View.GONE

                    for (child in layoutCount.children){
                        child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_corner)
                    }

                    if(editBox.text.isEmpty()){
                        val textInputLast = layoutCount.getChildAt(0) as TextView
                        textInputLast.text = "|"
                    }

                    editBox.requestFocus()
                    val inputManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.showSoftInput(editBox, InputMethodManager.SHOW_IMPLICIT)

                }

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
                    layoutCount.isEnabled = true

                    linearCountdown.visibility = View.VISIBLE
                    imgCorrect.visibility = View.GONE
                    imgWrong.visibility = View.GONE

                    for(child in layoutCount.children){
                        child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_corner)
                    }

                    layoutCount.performClick()
                }

                imgWrong.setOnClickListener {
                    for (child in layoutCount.children){
                        val children = child as TextView
                        children.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_corner)
                        children.text = ""
                    }
                    editBox.setText("")
                    imgWrong.visibility = View.GONE
                    linearCountdown.visibility = View.VISIBLE

                    layoutCount.performClick()
                }
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
                                val inputManager =
                                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
                            }
                        }
                    }


                    override fun afterTextChanged(p0: Editable?) {
                        if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty() && editBox.text.toString() == "111111"){
                            imgCorrect.visibility = View.VISIBLE
                            imgWrong.visibility = View.GONE
                            linearCountdown.visibility = View.GONE
                            stopTimer()
                            for(child in layoutCount.children){
                                child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_green_corner)
                            }
                            lifecycleScope.launch {
                                delay(1000)
                                findNavController().navigate(R.id.action_to_term_condition_security_fragment)
                                editBox.setText("")
                            }
                        } else if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty() && editBox.text.toString() != "111111" ){
                            imgCorrect.visibility = View.GONE
                            imgWrong.visibility = View.VISIBLE
                            linearCountdown.visibility = View.GONE
                            for(child in layoutCount.children){
                                child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_red_corner)
                            }
                            stopTimer()
                        }
                    }

                })

                btnNext.setOnClickListener {
                    val status: Int = 0
                    if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty()) {

                        pbNext.visibility = View.VISIBLE
                        btnNext.isClickable = false
                        btnNext.alpha = 0.6F

                        Handler().postDelayed({
                            if(editBox.text.toString() == "111111" || status == 1){
                                imgCorrect.visibility = View.VISIBLE
                                imgWrong.visibility = View.GONE
                                linearCountdown.visibility = View.GONE
                                stopTimer()
                                for(child in layoutCount.children){
                                    child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_green_corner)
                                }

                                when (arguments?.getString("title")) {
                                    "sign in" -> {
                                        SessionPreferences().SESSION_STATUS = true
                                        SessionVariable.SESSION_STATUS.value = true
                                        activity?.finish()
                                    }
                                    "reset password" -> {
                                        findNavController().navigate(R.id.action_to_new_password_security_fragment)
                                    }
                                    "register" -> {
                                        findNavController().navigate(R.id.action_to_term_condition_security_fragment)
                                    }
                                    "change login password"->{
                                        activity?.finish()
                                    }
                                    "forgot fund password" -> {
                                        activity?.finish()
                                    }
                                    "Forgot Fund Password" -> {
                                        val bundle = Bundle()
                                        bundle.putString("title", "Finish")
                                        findNavController().navigate(R.id.action_from_verify_to_new_fund_password,bundle)
                                    }
                                    "Forgot Login Password"-> {
                                        findNavController().navigate(R.id.action_to_new_account_login_password)
                                    }
                                    "add number" -> {
                                        activity?.finish()
                                    }
                                }
                            }else{
                                linearCountdown.visibility = View.GONE
                                imgCorrect.visibility = View.GONE
                                imgWrong.visibility = View.VISIBLE
                                for(child in layoutCount.children){
                                    child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_red_corner)
                                }
                            }

                            pbNext.visibility = View.GONE
                            btnNext.isClickable = true
                            btnNext.alpha = 1F

                        }, 3000)


                    } else {
                        linearCountdown.visibility = View.GONE
                        imgCorrect.visibility = View.GONE
                        imgWrong.visibility = View.VISIBLE
                        for(child in layoutCount.children){
                            child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_red_corner)
                        }
                    }
                }
            }
        }catch (error: Exception) {
            // Must be safe
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
                    Toast.makeText(
                        activity,
                        "You're run out of time!",
                        Toast.LENGTH_SHORT
                    ).show()
                    editBox.setText("")
                    layoutCount.isEnabled = false

                    linearCountdown.visibility = View.GONE
                    imgCorrect.visibility = View.GONE
                    imgWrong.visibility = View.VISIBLE

                    for(child in layoutCount.children){
                        child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_red_corner)
                    }
                }
            }.start()
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

}
