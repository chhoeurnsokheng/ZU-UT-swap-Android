package com.zillennium.utswap.module.kyc.kycActivity

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.IdTypeFragment
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SIgnInPresenter
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInView

class KYCActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivityKycBinding>(),
    SignInView.View {

    override var mPresenter: SignInView.Presenter = SIgnInPresenter()
    override val layoutResource: Int = R.layout.activity_kyc

    var bodyRequest :MutableList<User.Kyc> ? = null
    var mIdTypeFragment: IdTypeFragment? = null

    fun getBodyRequest(body:MutableList<User.Kyc>){
        getBodyRequest(body)
    }

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {

        }
    }

}