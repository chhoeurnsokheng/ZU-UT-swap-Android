package com.zillennium.utswap.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.LayoutBaseNoInternetBinding
import com.androidstudy.networkmanager.Tovuti

class NoInternetLayoutUtil {
    private var mBinding: LayoutBaseNoInternetBinding? = null
    private var container: RelativeLayout? = null

    fun noInternetLayoutUtil(layoutInternet: RelativeLayout) {

        container = layoutInternet
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container?.context),
            R.layout.layout_base_no_internet,
            null,
            false
        )
        container?.addView(mBinding?.root)
        initViewNoInternet()
        autoConnection()
    }

    private fun initViewNoInternet() {
        mBinding?.apply {
            btnTryAgain.setOnClickListener {
                it.backgroundTintList =
                    ContextCompat.getColorStateList(UTSwapApp.instance, R.color.dark_gray)
                progressBar.visibility = View.VISIBLE
            }

        }

    }

    private fun autoConnection() {
        Tovuti.from(container?.context).monitor { _, isConnected, _ ->
            if (isConnected) {
                container?.visibility = View.GONE
            } else {
                container?.visibility = View.VISIBLE
            }
        }
    }

}