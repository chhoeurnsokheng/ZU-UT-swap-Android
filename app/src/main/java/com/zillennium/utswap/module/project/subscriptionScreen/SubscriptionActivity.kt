package com.zillennium.utswap.module.project.subscriptionScreen

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectSubscriptionBinding

import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.project.subscriptionScreen.adapter.SubscriptionAdapter
import com.zillennium.utswap.module.project.subscriptionScreen.bottomSheet.SubscriptionBottomSheet
import com.zillennium.utswap.module.project.subscriptionScreen.dialog.SubscriptionConfirmDialog
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.Constants


class SubscriptionActivity :
    BaseMvpActivity<SubscriptionView.View, SubscriptionView.Presenter, ActivityProjectSubscriptionBinding>(),
    SubscriptionView.View {

    override var mPresenter: SubscriptionView.Presenter = SubscriptionPresenter()
    override val layoutResource: Int = R.layout.activity_project_subscription
    private var kycSubmit: Boolean? = false
    private var kycComplete: Boolean? = false
    private var mBottomSheet: SubscriptionBottomSheet? = null
    private var subscriptionList: ArrayList<SubscriptionProject.SubscriptionProjectData> =
        arrayListOf()
    private var date_range = ""
    private var projectName = ""
    private var userLevel = ""

    companion object {

        fun launchSubscriptionActivity(context: Context, id: String?, project_name: String?) {
            val intent = Intent(context, SubscriptionActivity::class.java)
            intent.putExtra("subscription_id", id)
            intent.putExtra(Constants.Project.ProjectName, project_name)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()

        binding.apply {
            mPresenter.onGetUserInfo(this@SubscriptionActivity)
            if (intent.hasExtra(Constants.Project.ProjectName)) {
                projectName = intent?.getStringExtra(Constants.Project.ProjectName).toString()
                txtSubscriptionTitle.text = projectName
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }
            SessionVariable.SESSION_SUBSCRIPTION_BOTTOM_SHEET.value = false
            SessionVariable.SESSION_SUBSCRIPTION_ORDER.value = false

            SessionVariable.SESSION_SUBSCRIPTION_ORDER.observe(this@SubscriptionActivity){
                if (it){
                    val id = intent?.getStringExtra("subscription_id")
                    mPresenter.onCheckSubscriptionStatus(
                        SubscriptionProject.SubscriptionProjectBody(
                            id?.toInt(),
                            ""
                        ), UTSwapApp.instance
                    )
                }
            }

            SessionVariable.SESSION_SUBSCRIPTION_BOTTOM_SHEET.observe(this@SubscriptionActivity) {
                if (it) {
                    val subscriptionConfirmDialog: SubscriptionConfirmDialog =
                        SubscriptionConfirmDialog.newInstance(
                            Constants.SubscriptionBottomSheet.id,
                            Constants.SubscriptionBottomSheet.volume,
                            Constants.SubscriptionBottomSheet.title,
                            Constants.SubscriptionBottomSheet.project_name,
                            Constants.SubscriptionBottomSheet.lock_time,
                            Constants.SubscriptionBottomSheet.volume_price,
                            Constants.SubscriptionBottomSheet.subscription
                        )
                    subscriptionConfirmDialog.show(
                        supportFragmentManager,
                        "balanceHistoryDetailDialog"
                    )
                }
            }

            SessionVariable.SESSION_STATUS.observe(this@SubscriptionActivity) {
                onCheckSessionStatusAndKYC()
            }

            SessionVariable.SESSION_KYC.observe(this@SubscriptionActivity) {
                onCheckSessionStatusAndKYC()
            }

        }
    }

    override fun onCheckKYCSuccess(data: User.KycRes) {
        kycSubmit = data.data?.status_submit_kyc
        kycComplete = data.data?.status_kyc
    }

    override fun onCheckKYCFail() {
    }

    private fun onCheckSessionStatusAndKYC() {
        binding.apply {
            if (SessionVariable.SESSION_STATUS.value == true && kycComplete == true) {
                recycleViewSubscriptionProject.alpha = 1F

            } else {
                recycleViewSubscriptionProject.alpha = 0.6F

            }
        }
    }

    /**   Subscription Project   **/
    override fun onCheckSubscriptionSuccess(data: SubscriptionProject.SubscriptionProjectRes) {
        binding.apply {
            data.data?.forEach {
                txtEndTime.text = it.endtime

            }
            /* Recycle view of project info detail */

            recycleViewSubscriptionProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            val subscriptionAdapter = SubscriptionAdapter(onclickAdapter, userLevel)
            subscriptionAdapter.items =
                data.data as ArrayList<SubscriptionProject.SubscriptionProjectData>
            recycleViewSubscriptionProject.adapter = subscriptionAdapter
            subscriptionAdapter.notifyDataSetChanged()

            if (SessionVariable.SESSION_STATUS.value == true && kycComplete == true) {
                recycleViewSubscriptionProject.alpha = 1F
            } else {
                recycleViewSubscriptionProject.alpha = 0.6F
            }

        }
    }

    override fun onCheckSubscriptionFail(data: SubscriptionProject.SubscriptionProjectRes) {}

    /**   User Profile Level      **/
    override fun onGetUserInfoSuccess(data: User.AppSideBarData) {
        userLevel = data.name_user_lavel.toString()
        if (intent.hasExtra("subscription_id")) {
            val id = intent?.getStringExtra("subscription_id")
            mPresenter.onCheckSubscriptionStatus(
                SubscriptionProject.SubscriptionProjectBody(
                    id?.toInt(),
                    ""
                ), UTSwapApp.instance
            )
        }
    }

    override fun onGetUserInfoFail(data: User.AppSideBarData) {
    }

    private val onclickAdapter: SubscriptionAdapter.OnclickAdapter =
        object : SubscriptionAdapter.OnclickAdapter {
            override fun onClickMe(title: String, lockTime: String, id: Int, volumePrice: Double) {
                if (SessionVariable.SESSION_STATUS.value == true && SessionVariable.SESSION_KYC.value == true) {
                    val subscriptionBottomSheetDialog: SubscriptionBottomSheet =
                        SubscriptionBottomSheet.newInstance(
                            id,
                            "",
                            title,
                            projectName,
                            lockTime,
                            volumePrice,
                            ""
                        )
                    subscriptionBottomSheetDialog.show(
                        supportFragmentManager,
                        "balanceHistoryDetailDialog"
                    )
                }
            }

        }

    override fun onDestroy() {
        super.onDestroy()
        SessionVariable.SESSION_SUBSCRIPTION_BOTTOM_SHEET.value = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        SessionVariable.SESSION_SUBSCRIPTION_BOTTOM_SHEET.value = false
    }

}
