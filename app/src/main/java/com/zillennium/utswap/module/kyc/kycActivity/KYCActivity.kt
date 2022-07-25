package com.zillennium.utswap.module.kyc.kycActivity

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycBinding
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.IdTypeFragment
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SIgnInPresenter
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInView

class KYCActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivityKycBinding>(),
    SignInView.View {

    override var mPresenter: SignInView.Presenter = SIgnInPresenter()
    override val layoutResource: Int = R.layout.activity_kyc

    var mIdTypeFragment: IdTypeFragment? = null

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {
            // Must be safe
        }
    }

}