package com.zillennium.utswap.screens.account.lockTimeOut

import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityLockTimeOutBinding

class LockTimeOutActivity :
    BaseMvpActivity<LockTimeOutView.View, LockTimeOutView.Presenter, ActivityLockTimeOutBinding>(),
    LockTimeOutView.View {

    override var mPresenter: LockTimeOutView.Presenter = LockTimeOutPresenter()
    override val layoutResource: Int = R.layout.activity_lock_time_out

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

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

                imgNotification.setOnClickListener {
                    //intent to notification screen
                    //from branch notification in git
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}