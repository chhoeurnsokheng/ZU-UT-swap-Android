package com.zillennium.utswap.screens.security.securityDialog

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogSecurityFundPasswordBinding
import eightbitlab.com.blurview.RenderScriptBlur
import java.sql.Struct


class FundPasswordDialog: DialogFragment() {

    private var binding: DialogSecurityFundPasswordBinding? = null
    private var codes: String = ""

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_security_fund_password, container, true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            activity?.apply {
                blurView.setupWith(findViewById<ViewGroup>(android.R.id.content))
                    .setFrameClearDrawable(window.decorView.background)
                    .setBlurAlgorithm(RenderScriptBlur(UTSwapApp.instance))
                    .setBlurRadius(20f)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true)

                imgBack.setOnClickListener {
                    dismiss()
                }

                val numberList = arrayListOf(
                    number0,
                    number1,
                    number2,
                    number3,
                    number4,
                    number5,
                    number6,
                    number7,
                    number8,
                    number9,
                )

                numberList.forEachIndexed { index, element ->
                    element.setOnClickListener {
                        setNumber(index)
                    }
                }

                removeNumber.setOnClickListener { removeNumber()  }

            }

        }
    }

    private fun setNumber(number: Number){
        codes += if(codes.length < 4){ number.toString() } else { "" }
        setPingCode()
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    private fun setPingCode(){
        binding?.apply {
            for (pingCode in layPingCode.children){
                pingCode.background = resources.getDrawable(R.drawable.bg_circular_border)
            }

            for (i in 0..codes.length - 1) {
                layPingCode.getChildAt(i).background = resources.getDrawable(R.drawable.bg_circular)
            }

            if(codes.length == 4){
                if(codes == "1111"){
                    imgIcon.setImageResource(R.drawable.ic_fund_key_success)
                    txtMessage.text = "Success"
                    txtMessage.setTextColor(resources.getColor(R.color.success))
                    for (pingCode in layPingCode.children){
                        pingCode.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.success))
                    }
                    dismiss()
                }else{
                    imgIcon.setImageResource(R.drawable.ic_fund_key_invalid)
                    txtMessage.text = "Invalid"
                    txtMessage.setTextColor(resources.getColor(R.color.main_red))
                    for (pingCode in layPingCode.children){
                        pingCode.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.main_red))
                    }
                }
            }else{
                imgIcon.setImageResource(R.drawable.ic_fund_key_normal)
                txtMessage.text = "Enter Fund Password"
                txtMessage.setTextColor(resources.getColor(R.color.color_main))
                for (pingCode in layPingCode.children){
                    pingCode.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.color_main))
                }
            }

        }
    }

    private fun removeNumber(){
        if(codes.length in 1..4){
            codes = codes.substring(0, codes.length -1)
            setPingCode()
        }

    }

    companion object {
        fun newInstance(): FundPasswordDialog {
            val subscriptionConfirmDialog = FundPasswordDialog()
            val args = Bundle()
            subscriptionConfirmDialog.arguments = args
            return subscriptionConfirmDialog
        }
    }

}