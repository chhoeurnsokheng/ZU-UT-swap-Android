package com.zillennium.utswap.screens.security.securityFragment.resetPassword

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.databinding.ActivitySecurityResetPasswordBinding

import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityResetPasswordBinding
import com.zillennium.utswap.screens.security.securityFragment.verificationScreen.VerificationFragment


class ResetPasswordFragment :
    BaseMvpFragment<ResetPasswordView.View, ResetPasswordView.Presenter, FragmentSecurityResetPasswordBinding>(),
    ResetPasswordView.View {

    override var mPresenter: ResetPasswordView.Presenter = ResetPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_security_reset_password

    override fun initView() {
        super.initView()

        try {
            binding.apply {
                imgBack.setOnClickListener {
                    activity?.finish()
                }
                btnNext.setOnClickListener {
                    var isHaveError = false
                    if (inputEmail.text.toString().isEmpty()) {
                        textEmpty.visibility = View.VISIBLE
                        inputEmail.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    else{
                        val title = ""
                        val bundle = bundleOf("reset password" to title)
                        findNavController().navigate(R.id.action_to_verification_security_fragment, bundle)
//                        val intent = Intent(UTSwapApp.instance, VerificationFragment::class.java)
//                        startActivity(intent)
                    }
                }

                inputEmail.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        textEmpty.visibility = View.GONE
                        inputEmail.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                    }
                })
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
    private fun resetPassWord(): Boolean {
        binding.apply {
            val resetPassword = inputEmail.text.toString().trim()
            val drawable: Drawable = inputEmail.background // get current EditText drawable
            drawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

            if (resetPassword.isEmpty()) {
                textEmpty.visibility = View.VISIBLE
                return false
            } else {
                textEmpty.visibility = View.GONE
                return true
            }
        }
    }
}
