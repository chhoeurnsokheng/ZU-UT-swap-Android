package com.zillennium.utswap.screens.navbar.projectTab.projectActivity

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.ActivityKycBinding
import com.zillennium.utswap.databinding.ActivityProjectBinding
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.IdTypeFragment
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SIgnInPresenter
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInView

class ProjectMainActivity :
    BaseMvpFragment<ProjectMainView.View, ProjectMainView.Presenter, ActivityProjectBinding>(),
    ProjectMainView.View {

    override var mPresenter: ProjectMainView.Presenter = ProjectMainPresenter()
    override val layoutResource: Int = R.layout.activity_project

    var mIdTypeFragment: IdTypeFragment? = null

    override fun initView() {
        super.initView()
        try {

//            Navigation.findNavController(this, R.id.kyc_nav_host_fragment).addOnDestinationChangedListener{  _, destination, _ ->
//                when(destination.id){
//                    R.id.IDVerificationKycFragment,
//                        R.id.SelfieHoldingKycFragment,
//                        R.id.EmploymentInfoKycFragment,
//                        R.id.DeclarationKycFragment,
//                        R.id.FundPasswordKycFragment,
//                        R.id.ContractKycFragment,
//                        R.id.KycApplicationKycFragment,
//                        -> {
//
//                        }
//                }
//
//            }



        } catch (error: Exception) {
            // Must be safe
        }
    }

}