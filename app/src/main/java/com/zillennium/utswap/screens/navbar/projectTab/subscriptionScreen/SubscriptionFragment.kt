package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarProjectSubscriptionBinding

import com.zillennium.utswap.models.SubscriptionModel
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.adapter.SubscriptionAdapter
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.bottomSheet.SubscriptionBottomSheet


class SubscriptionFragment :
    BaseMvpFragment<SubscriptionView.View, SubscriptionView.Presenter, FragmentNavbarProjectSubscriptionBinding>(),
    SubscriptionView.View {

    override var mPresenter: SubscriptionView.Presenter = SubscriptionPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_project_subscription

    override fun initView() {
        super.initView()
        try {
//            Handler().postDelayed({
                binding.apply {

                    btnBack.setOnClickListener {
                        findNavController().popBackStack()
                    }

                    SessionVariable.SESSION_STATUS.observe(requireActivity()) {
                        onCheckSessionStatusAndKYC()
                    }

                    SessionVariable.SESSION_KYC.observe(requireActivity()){
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
                subscriptionBottomSheetDialog.show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
            }
        }

    }

}
