package com.zillennium.utswap.module.security.securityFragment.changePasswordSuccess

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentChangePasswordSuccessBinding

class ChangePasswordSuccessFragment :
    BaseMvpFragment<ChangePasswordSuccessView.View, ChangePasswordSuccessView.Presenter, FragmentChangePasswordSuccessBinding>(),
    ChangePasswordSuccessView.View {

    override var mPresenter: ChangePasswordSuccessView.Presenter = ChangePasswordSuccessPresenter()
    override val layoutResource: Int = R.layout.fragment_change_password_success

    override fun initView() {
        super.initView()
        binding.apply {
            btnDone.setOnClickListener {
                activity?.finish()
            }
        }
    }

}