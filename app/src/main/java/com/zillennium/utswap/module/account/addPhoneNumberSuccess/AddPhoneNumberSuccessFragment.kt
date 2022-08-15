package com.zillennium.utswap.module.account.addPhoneNumberSuccess

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAddPhoneNumberSuccessBinding

class AddPhoneNumberSuccessFragment :
    BaseMvpFragment<AddPhoneNumberSuccessView.View, AddPhoneNumberSuccessView.Presenter, FragmentAddPhoneNumberSuccessBinding>(),
    AddPhoneNumberSuccessView.View {

    override var mPresenter: AddPhoneNumberSuccessView.Presenter = AddPhoneNumberSuccessPresenter()
    override val layoutResource: Int = R.layout.fragment_add_phone_number_success

    override fun initView() {
        super.initView()
        binding.apply {
            btnDone.setOnClickListener {
                activity?.finish()
            }
        }
    }

}