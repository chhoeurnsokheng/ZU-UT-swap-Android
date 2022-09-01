package com.zillennium.utswap.module.security.securityFragment.changeFundPasswordSuccess

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentChangeFundPasswordSuccessBinding

class ChangeFundPasswordSuccessFragment :
    BaseMvpFragment<ChangeFundPasswordSuccessView.View, ChangeFundPasswordSuccessView.Presenter, FragmentChangeFundPasswordSuccessBinding>(),
    ChangeFundPasswordSuccessView.View {

    override var mPresenter: ChangeFundPasswordSuccessView.Presenter = ChangeFundPasswordSuccessPresenter()
    override val layoutResource: Int = R.layout.fragment_change_fund_password_success

    override fun initView() {
        super.initView()
        binding.apply {
            btnDone.setOnClickListener {
                activity?.finish()
            }
        }
    }

}