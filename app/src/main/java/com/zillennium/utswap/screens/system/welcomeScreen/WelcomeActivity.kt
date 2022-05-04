package com.zillennium.utswap.screens.system.welcomeScreen

import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.widget.TextView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemWelcomeBinding
import com.zillennium.utswap.screens.security.signInScreen.SignInActivity
import com.zillennium.utswap.screens.security.signUpScreen.SignUpActivity
import com.zillennium.utswap.screens.system.welcomeScreen.adpater.MyAdapter
import java.io.IOException

class WelcomeActivity :
    BaseMvpActivity<WelcomeView.View, WelcomeView.Presenter, ActivitySystemWelcomeBinding>(),
    WelcomeView.View {

    override var mPresenter: WelcomeView.Presenter = WelcomePresenter()
    override val layoutResource: Int = R.layout.activity_system_welcome

    lateinit var layouts: IntArray

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                supportActionBar?.hide()

                layouts = intArrayOf(
                    R.layout.fragment_welcome_slide_1,
                    R.layout.fragment_welcome_slide_2,
                    R.layout.fragment_welcome_slide_3,
                    R.layout.fragment_welcome_slide_4
                )
                val myAdapter = MyAdapter(layouts, applicationContext)
                welcomeSlider.adapter = myAdapter
                welcomeSlider.addOnPageChangeListener(object : OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        setDots(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {}
                })
                setDots(0)

                btnSignIn.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                btnRegister.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }

    fun setDots(page: Int) {
        binding.apply {
            dotsLayout.removeAllViews()
            val dostTv = arrayOfNulls<TextView>(layouts.size)
            for (i in dostTv.indices) {
                dostTv[i] = TextView(this@WelcomeActivity)
                dostTv[i]?.text = Html.fromHtml("&#8226;")
                dostTv[i]?.textSize = 30f
                dostTv[i]?.setTextColor(Color.parseColor("#a9b4bb"))
                dotsLayout.addView(dostTv[i])
            }
            if (dostTv.isNotEmpty()) {
                dostTv[page]?.setTextColor(Color.parseColor("#FFFF00"))
            }
        }

    }
}