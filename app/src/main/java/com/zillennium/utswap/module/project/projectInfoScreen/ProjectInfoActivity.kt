package com.zillennium.utswap.module.project.projectInfoScreen

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectInfoBinding
import com.zillennium.utswap.models.projectList.ProjectInfoDetail
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity


class ProjectInfoActivity :
    BaseMvpActivity<ProjectInfoView.View, ProjectInfoView.Presenter, ActivityProjectInfoBinding>(),
    ProjectInfoView.View {

    override var mPresenter: ProjectInfoView.Presenter = ProjectInfoPresenter()
    override val layoutResource: Int = R.layout.activity_project_info

    private var projectDetail: ArrayList<ProjectInfoDetail.ProjectInfoDetailData> = arrayListOf()
    private var projectInfoDetailsAdapter: ProjectInfoDetailsAdapter =
        ProjectInfoDetailsAdapter(R.layout.item_list_project_info_details)


    private var termCondition = true
    private var condition = true
    private var imagesSlider: ArrayList<String> = arrayListOf()
    private var id: String? = null
    override fun initView() {

        super.initView()
        binding.apply {
            val intent = intent
//            id = intent.getStringExtra("id")
//            mPresenter.projectDetail(
//                id,
//                UTSwapApp.instance
//            )

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnSubscriptTrade.setOnClickListener {
                val intent: Intent = Intent(UTSwapApp.instance, SubscriptionActivity::class.java)
                startActivity(intent)
            }

            onCallApi()
        }


    }

    override fun projectDetailSuccess(data: ProjectInfoDetail.ProjectInfoDetailData) {
        binding.apply {
            /* Image Slider with View Pager and TabLayout*/
            println("=============data================" + data)

            /* Recycle view of project info detail */
            val titleInfoDetail = arrayOf(
                "Title Deed",
                "Land Size",
                "Total UT",
                "Base Price",
                "Target Price",
                "Managed by",
                "Location"
            )

            rvProjectInfoDetail.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            rvProjectInfoDetail.adapter = projectInfoDetailsAdapter
            projectInfoDetailsAdapter.items = projectDetail


        }
    }

    override fun projectDetailFail(data: ProjectInfoDetail.ProjectInfoDetailRes) {
        TODO("Not yet implemented")
    }

    private fun onCallApi() {
//        mPresenter.projectDetail(
//            id,
//            UTSwapApp.instance
//        )
    }

}
