package com.zillennium.utswap.screens.account.verificationAccunt

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityVerifictionAccountBinding
import com.zillennium.utswap.screens.account.activity.accountDetail.AccountDetailActivity

class VerificationAccountActivity :
    BaseMvpActivity<VerificationAccountView.View, VerificationAccountView.Presenter, ActivityVerifictionAccountBinding>(),
    VerificationAccountView.View {

    override var mPresenter: VerificationAccountView.Presenter = VerificationAccountPresenter()
    override val layoutResource: Int = R.layout.activity_verifiction_account

    private var countDownTimer: CountDownTimer? = null
    private val timeLeftInMilliseconds: Long = 120000 //120 second = 120000

    var view = this.currentFocus

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                startTimer()

                val intent = intent
                val text = intent.getStringExtra(Intent.EXTRA_TEXT)

                title.text = text.toString()

                imgClose.setOnClickListener {
                    finish()
                }

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
                    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.showSoftInput(editBox, InputMethodManager.SHOW_IMPLICIT)

                }

                resendCode.setOnClickListener {
                    stopTimer()
                    startTimer()
                    btnNext.isEnabled = true
                    Toast.makeText(
                        this@VerificationAccountActivity,
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
                                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
                            }
                        }
                    }

                    override fun afterTextChanged(p0: Editable?) {}

                })

                btnNext.setOnClickListener {
                    val status: Int = 0
                    if (editBox.text.toString().length == 6 && editBox.text.toString().isNotEmpty()) {

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

                                this@VerificationAccountActivity.finish()

                                val intent = Intent(UTSwapApp.instance,AccountDetailActivity::class.java)
                                startActivity(intent)
                            }else{
                                linearCountdown.visibility = View.GONE
                                imgCorrect.visibility = View.GONE
                                imgWrong.visibility = View.VISIBLE
                                for(child in layoutCount.children){
                                    child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_red_corner)
                                }
                            }

                            btnNext.isClickable = true
                            btnNext.alpha = 1F

                        }, 1000)


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
            // Code
        } catch (error: Exception) {
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
                        this@VerificationAccountActivity,
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