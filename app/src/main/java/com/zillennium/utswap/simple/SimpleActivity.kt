package com.zillennium.utswap.simple

import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.example.utswapapp.simple.SimplePresenter
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ActivitySimpleBinding
import java.io.IOException

class SimpleActivity :
    BaseMvpActivity<SimpleView.View, SimpleView.Presenter, ActivitySimpleBinding>(),
    SimpleView.View {

    override var mPresenter: SimpleView.Presenter = SimplePresenter()
    override val layoutResource: Int = R.layout.activity_simple

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                // Code
            }
        } catch (error: IOException) {
            // Must be safe
        }
    }
}