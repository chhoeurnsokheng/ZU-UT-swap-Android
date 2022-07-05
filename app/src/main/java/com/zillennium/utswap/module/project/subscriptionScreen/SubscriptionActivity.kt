package com.zillennium.utswap.module.project.subscriptionScreen

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectSubscriptionBinding

import com.zillennium.utswap.models.SubscriptionModel
import com.zillennium.utswap.module.project.subscriptionScreen.adapter.SubscriptionAdapter
import com.zillennium.utswap.module.project.subscriptionScreen.bottomSheet.SubscriptionBottomSheet


class SubscriptionActivity :
    BaseMvpActivity<SubscriptionView.View, SubscriptionView.Presenter, ActivityProjectSubscriptionBinding>(),
    SubscriptionView.View {

    override var mPresenter: SubscriptionView.Presenter = SubscriptionPresenter()
    override val layoutResource: Int = R.layout.activity_project_subscription

    override fun initView() {
        super.initView()
        try {
//            Handler().postDelayed({
                binding.apply {

                    btnBack.setOnClickListener {
                        onBackPressed()
                    }

                    SessionVariable.SESSION_STATUS.observe(this@SubscriptionActivity) {
                        onCheckSessionStatusAndKYC()
                    }

                    SessionVariable.SESSION_KYC.observe(this@SubscriptionActivity){
                        onCheckSessionStatusAndKYC()
                    }

                    /* Recycle view of project info detail */
                    val tvTitle = arrayOf(
                        "Professional",
                        "Premium, Professional",
                        "Standard, Premium, Professional"
                    )

                    val tvDollar = arrayOf(
                        3.90,
                        1023.98987,
                        4.10
                    )
                    val tvDayLock = arrayOf(
                        "60",
                        "40 ",
                        "No Lock"
                    )

                    val tvUtValue = arrayOf(
                        0,
                        11553,
                        24753
                    )

                    val tvUtMainValue = arrayOf(
                        44000,
                        77000,
                        99000000
                    )

                    val subscriptionArrayList = arrayListOf<SubscriptionModel>()
                    for (i in tvTitle.indices) {
                        val subscriptionInfo = SubscriptionModel(
                            tvTitle[i],
                            tvDollar[i],
                            tvDayLock[i],
                            tvUtValue[i],
                            tvUtMainValue[i]
                        )
                        subscriptionArrayList.add(subscriptionInfo)
                    }
                    recycleViewProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    recycleViewProject.adapter = SubscriptionAdapter(subscriptionArrayList, onclickAdapter)

                    if(SessionVariable.SESSION_STATUS.value  == true && SessionVariable.SESSION_STATUS.value  == true){
                        recycleViewProject.alpha = 1F
                    } else{
                        recycleViewProject.alpha = 0.6F
                    }

                }
//            }, 5000)


        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun onCheckSessionStatusAndKYC(){
        binding.apply {
            if(SessionVariable.SESSION_STATUS.value == true && SessionVariable.SESSION_KYC.value == true){
                recycleViewProject.alpha = 1F
            }else{
                recycleViewProject.alpha = 0.6F
            }
        }
    }

    private val onclickAdapter: SubscriptionAdapter.OnclickAdapter = object: SubscriptionAdapter.OnclickAdapter{
        override fun onClickMe(subscriptionModel: SubscriptionModel) {
            if(SessionVariable.SESSION_STATUS.value == true && SessionVariable.SESSION_KYC.value == true){
                val subscriptionBottomSheetDialog: SubscriptionBottomSheet = SubscriptionBottomSheet.newInstance(
                    subscriptionModel.tv_title,
                )
                subscriptionBottomSheetDialog.show(supportFragmentManager, "balanceHistoryDetailDialog")
            }
        }

    }

}
