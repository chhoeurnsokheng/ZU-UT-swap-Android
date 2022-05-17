package com.zillennium.utswap.screens.security.securityFragment.verificationScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityVerificationBinding

class VerificationFragment():
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

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                startTimer()

                layoutCount.setOnClickListener {
                    linearCountdown.visibility = View.VISIBLE
                    imgWrong.visibility = View.GONE
                    imgCorrect.visibility = View.GONE

                    for (child in layoutCount.children){
                        child.background = resources.getDrawable(R.drawable.bg_corner)
                    }

                    editBox.requestFocus()
                    val inputManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.showSoftInput(editBox, InputMethodManager.SHOW_IMPLICIT)

                }
                layoutCount.performClick()

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
                        child.background = resources.getDrawable(R.drawable.bg_corner)
                    }
                }

                imgWrong.setOnClickListener {
                    for (child in layoutCount.children){
                        val children = child as TextView
                        children.background = resources.getDrawable(R.drawable.bg_corner)
                        children.text = ""
                    }
                    imgWrong.visibility = View.GONE
                    linearCountdown.visibility = View.VISIBLE
                }

                editBox.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        for (child in layoutCount.children){
                            val children = child as TextView
                            children.text = ""
                        }

                        for (index in chr?.indices!!) {
                            val textInput = layoutCount.getChildAt(index) as TextView
                            textInput.text = chr[index].toString()

                            if(index == layoutCount.childCount - 1){
                                val inputManager =
                                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
                            }
                        }
                    }

                    override fun afterTextChanged(p0: Editable?) {}

                })

                btnNext.setOnClickListener {

                    if (editBox.text.toString() == "111111") {
                        imgCorrect.visibility = View.VISIBLE
                        imgWrong.visibility = View.GONE
                        linearCountdown.visibility = View.GONE
                        stopTimer()
                        for(child in layoutCount.children){
                            child.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        }

                        when (arguments?.getString("title")) {
                            "sign in" -> {
                                SessionPreferences().SESSION_STATUS = true
                                activity?.finish()
                            }
                            "reset password" -> {
                                findNavController().navigate(R.id.action_to_new_password_security_fragment)
                            }
                            "register" -> {
                                findNavController().navigate(R.id.action_to_term_condition_security_fragment)
                            }
                        }

                    } else {
                        linearCountdown.visibility = View.GONE
                        imgCorrect.visibility = View.GONE
                        imgWrong.visibility = View.VISIBLE

                        for(child in layoutCount.children){
                            child.background = resources.getDrawable(R.drawable.bg_red_corner)
                        }
                    }

//                    if (editBox.text.toString().isEmpty() || editBox.text.toString().length < 6)
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
                        requireActivity(),
                        "You're run out of time!",
                        Toast.LENGTH_SHORT
                    ).show()
                    editBox.setText("")
                    layoutCount.isEnabled = false

                    linearCountdown.visibility = View.GONE
                    imgCorrect.visibility = View.GONE
                    imgWrong.visibility = View.VISIBLE

                    for(child in layoutCount.children){
                        child.background = resources.getDrawable(R.drawable.bg_red_corner)
                    }
                }
            }.start()
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

}
