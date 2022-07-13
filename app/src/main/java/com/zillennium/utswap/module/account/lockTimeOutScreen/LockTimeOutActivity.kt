package com.zillennium.utswap.module.account.lockTimeOutScreen

import android.view.View
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountLockTimeOutBinding

class LockTimeOutActivity :
    BaseMvpActivity<LockTimeOutView.View, LockTimeOutView.Presenter, ActivityAccountLockTimeOutBinding>(),
    LockTimeOutView.View {

    override var mPresenter: LockTimeOutView.Presenter = LockTimeOutPresenter()
    override val layoutResource: Int = R.layout.activity_account_lock_time_out

    override fun initView() {
        super.initView()
        try {
            toolBar()
            binding.apply {

                btn5Minutes.setOnClickListener {
                    imgCheck10Minutes.visibility = View.GONE
                    imgCheck5Minutes.visibility = View.VISIBLE
                    imgCheck30Minutes.visibility = View.GONE
                }

                btn10Minutes.setOnClickListener {
                    imgCheck10Minutes.visibility = View.VISIBLE
                    imgCheck5Minutes.visibility = View.GONE
                    imgCheck30Minutes.visibility = View.GONE
                }

                btn30Minutes.setOnClickListener {
                    imgCheck10Minutes.visibility = View.GONE
                    imgCheck5Minutes.visibility = View.GONE
                    imgCheck30Minutes.visibility = View.VISIBLE
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.lock_time_out)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }


}