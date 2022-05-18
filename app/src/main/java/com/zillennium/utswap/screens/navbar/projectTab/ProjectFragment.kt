package com.zillennium.utswap.screens.navbar.projectTab

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarProjectBinding

class ProjectFragment :
    BaseMvpFragment<ProjectView.View, ProjectView.Presenter, FragmentNavbarProjectBinding>(),
    ProjectView.View {

    override var mPresenter: ProjectView.Presenter = ProjectPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_project

    override fun initView() {
        super.initView()
        try {
            binding.apply {



            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}