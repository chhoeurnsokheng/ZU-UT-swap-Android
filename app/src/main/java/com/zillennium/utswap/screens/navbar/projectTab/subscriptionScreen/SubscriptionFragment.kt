package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarProjectInfoBinding
import com.zillennium.utswap.databinding.FragmentNavbarProjectSubscriptionBinding
import com.zillennium.utswap.models.ProjectInfoDetail
import com.zillennium.utswap.models.ProjectInfoInvestment
import com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter.ProjectInfoInvestmentAdapter
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.bottomSheet.SubscriptionBottomSheet
import java.util.*


class SubscriptionFragment :
    BaseMvpFragment<SubscriptionView.View, SubscriptionView.Presenter, FragmentNavbarProjectSubscriptionBinding>(),
    SubscriptionView.View {

    override var mPresenter: SubscriptionView.Presenter = SubscriptionPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_project_subscription

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                CardViewPopup.setOnClickListener {
                    val subscriptionBottomSheetDialog: SubscriptionBottomSheet = SubscriptionBottomSheet.newInstance()
                    subscriptionBottomSheetDialog.show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }


    }
}
