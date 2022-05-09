package com.zillennium.utswap.screens.security.verificationScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityVerificationBinding
import com.zillennium.utswap.screens.kyc.termConditionScreen.TermConditionActivity
import com.zillennium.utswap.screens.security.newPasswordScreen.NewPasswordActivity

class VerificationActivity :
    BaseMvpActivity<VerificationView.View, VerificationView.Presenter, ActivitySecurityVerificationBinding>(),
    VerificationView.View {

    override var mPresenter: VerificationView.Presenter = VerificationPresenter()
    override val layoutResource: Int = R.layout.activity_security_verification

    private var countDownTimer: CountDownTimer? = null
    private val timeLeftInMilliseconds: Long = 12000 //120 second


    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {
                    val intent = Intent(
                        UTSwapApp.instance,
                        TermConditionActivity::class.java
                    )
                    startActivity(intent)
                }

                btnResend.setOnClickListener {
                    startTimer()
                }

                setupOTPInputs()

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun setupOTPInputs() {
        binding.apply {
            startTimer()
            editBox1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                        editBox2.requestFocus()
                    }
                }

                override fun afterTextChanged(editable: Editable) {}
            })
            editBox2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                        editBox3.requestFocus()
                    } else editBox1.requestFocus()
                }

                override fun afterTextChanged(editable: Editable) {}
            })
            editBox3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                        editBox4.requestFocus()
                    } else editBox2.requestFocus()
                }

                override fun afterTextChanged(editable: Editable) {}
            })
            editBox4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                        editBox5.requestFocus()
                    } else editBox3.requestFocus()
                }

                override fun afterTextChanged(editable: Editable) {}
            })
            editBox5.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                        editBox6.requestFocus()
                    } else editBox4.requestFocus()
                }

                override fun afterTextChanged(editable: Editable) {}
            })
            editBox6.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                        imgCorrect.visibility = View.VISIBLE
                        linearCountdown.visibility = View.GONE
                        editBox1.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        editBox2.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        editBox3.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        editBox4.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        editBox5.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        editBox6.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                        stopTimer()
                    } else {
                        imgCorrect.visibility = View.GONE
                        linearCountdown.visibility = View.VISIBLE
                        editBox5.requestFocus()
                        editBox1.background = resources.getDrawable(R.drawable.bg_corner)
                        editBox2.background = resources.getDrawable(R.drawable.bg_corner)
                        editBox3.background = resources.getDrawable(R.drawable.bg_corner)
                        editBox4.background = resources.getDrawable(R.drawable.bg_corner)
                        editBox5.background = resources.getDrawable(R.drawable.bg_corner)
                        editBox6.background = resources.getDrawable(R.drawable.bg_corner)
                    }
                }

                override fun afterTextChanged(editable: Editable) {
//                    resendMessage.visibility = View.VISIBLE
//                    btnNext.setOnClickListener(View.OnClickListener { view: View? ->
//                        val intent = Intent(applicationContext, NewPasswordActivity::class.java)
//                        startActivity(intent)
//                    })
                }
            })
        }

    }

    fun startTimer() {
        binding.apply {
            countDownTimer?.cancel()
            countDownTimer = object : CountDownTimer(120000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(l: Long) {
                    txtCountDown.text = "" + l / 1000
                }

                override fun onFinish() {}
            }.start()
        }
    }

    fun stopTimer() {
        countDownTimer?.cancel()
    }

}
