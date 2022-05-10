package com.zillennium.utswap.screens.navbar.projectTab

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarProjectBinding
import com.zillennium.utswap.screens.project.projecrDetailScreen.ProjectDetailActivity
import com.zillennium.utswap.screens.setting.settingScreen.SettingActivity
import com.zillennium.utswap.screens.system.notificationScreen.NotificationActivity
import com.zillennium.utswap.screens.system.scanQRCodeScreen.ScanQRCodeActivity
import com.zillennium.utswap.screens.system.searchScreen.SearchActivity
import java.io.IOException

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