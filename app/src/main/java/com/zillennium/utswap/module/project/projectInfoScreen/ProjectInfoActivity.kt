package com.zillennium.utswap.module.project.projectInfoScreen

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.androidstudy.networkmanager.Tovuti
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectInfoBinding
import com.zillennium.utswap.models.ProjectInfoDetailModel
import com.zillennium.utswap.models.ProjectInfoSlideImageModel
import com.zillennium.utswap.models.ViewImageModel
import com.zillennium.utswap.models.project.ProjectInfoDetail
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.module.project.ViewImage.ImageViewActivity
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectViewPagerAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.dialog.DialogProjectSliderImage
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity
import com.zillennium.utswap.utils.Constants


class ProjectInfoActivity() :
    BaseMvpActivity<ProjectInfoView.View, ProjectInfoView.Presenter, ActivityProjectInfoBinding>(),
    ProjectInfoView.View {

    override var mPresenter: ProjectInfoView.Presenter = ProjectInfoPresenter()
    override val layoutResource: Int = R.layout.activity_project_info

    private var termCondition = true
    private var condition = true
    private var imagesSlider: ArrayList<String> = arrayListOf()
    private var id: Int = 0

    companion object {
        fun launchProjectInfoActivity(context: Context, id: String?) {
            val intent = Intent(context, ProjectInfoActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }



    override fun initView() {

        super.initView()
        val intent = intent
        id = intent.extras!!.getString("id")!!.toInt()

        if (intent.hasExtra("id")) {
            val id = intent?.getStringExtra("id")

            id?.toInt()?.let { ProjectInfoDetail.ProjectInfoDetailObject(it) }?.let {
                mPresenter.projectInfoView(
                    it,
                    UTSwapApp.instance
                )
            }
        }





        binding.apply {
            onCallApi()

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnSubscriptTrade.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SubscriptionActivity::class.java)
                startActivity(intent)
            }

        }
    }

    override fun projectInfoViewSuccess(data: ProjectInfoDetail.ProjectInfoDetailData) {
        binding.apply {
            progressbarGetData.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
            viewBackground.visibility = View.GONE

            /* Image Slider with View Pager and TabLayout*/
            imagesSlider = arrayListOf()
            imagesSlider.addAll(data.images)
            val adapter = ProjectViewPagerAdapter(onclickAdapter)
            adapter.items = imagesSlider
            imageSlideViewPager.adapter = adapter
            TabLayoutMediator(tabLayoutDot, imageSlideViewPager) { tab, position ->
            }.attach()

            imageSlideViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {})

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
            val valueInfo = arrayOf(
                data.title_deed,
                data.land_size,
                "${data.total_ut} UT",
                data.base_price,
                data.target_price,
                data.managed_by,
                data.location
            )

            val projectInfoDetailArrayList = arrayListOf<ProjectInfoDetailModel>()

            for (i in valueInfo.indices) {
                val projectInfo = ProjectInfoDetailModel(
                    titleInfoDetail[i],
                    valueInfo[i] as Any
                )
                projectInfoDetailArrayList.add(projectInfo)
            }

            rvProjectInfoDetail.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            val projectAdapter = ProjectInfoDetailsAdapter()
            projectAdapter.items = projectInfoDetailArrayList
            rvProjectInfoDetail.adapter = projectAdapter

            // Google map
            layGoogleMap.setOnClickListener {
                val url = data.google_map_link
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }


            /* Recycle Investment info */
            val investmentInformation = data.investment_information
            if (!investmentInformation.isNullOrEmpty()) {
                mainLayoutUt.visibility = View.VISIBLE
                txtInvestmentInfo.visibility = View.VISIBLE
                layoutBaseAndTargetPrice.visibility = View.VISIBLE
            } else {
                mainLayoutUt.visibility = View.GONE
                txtInvestmentInfo.visibility = View.GONE
                layoutBaseAndTargetPrice.visibility = View.GONE
            }
//            val perUT = arrayOf(
//
//                "4.10",
//                "4.55",
//            )
//            val valueUT = arrayOf(
//                "902 000",
//                "1 001 000",
//            )
//            val sqmUT = arrayOf(
//                "1 625",
//                "1 804",
//            )
//
//            val projectInfoInvestmentArrayList = arrayListOf<ProjectInfoInvestmentModel>()
//
//            for (i in perUT.indices) {
//                val projectInvestment = ProjectInfoInvestmentModel(
//                    perUT[i],
//                    valueUT[i],
//                    sqmUT[i]
//                )
//                projectInfoInvestmentArrayList.add(projectInvestment)
//            }
//
//            rvProjectInvestmentInfo.layoutManager = LinearLayoutManager(UTSwapApp.instance)
//            val projectInvestmentAdapter = ProjectInfoInvestmentAdapter()
//            projectInvestmentAdapter.items = projectInfoInvestmentArrayList
//            rvProjectInvestmentInfo.adapter = projectInvestmentAdapter


            /* Term and condition */
            txtTermCondition.text = data.term_and_condition
            layoutTermCondition.setOnClickListener {
                if (termCondition) {
                    arrowDownTermCondition.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    txtTermCondition.visibility = View.VISIBLE
                    termCondition = !termCondition
                } else {
                    arrowDownTermCondition.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    txtTermCondition.visibility = View.GONE
                    termCondition = !termCondition
                }
            }

            //Documentation PDF
            val document = data.documents
            if (document.isNullOrEmpty()) {
                layoutDocument.visibility = View.GONE
                documentLine.visibility = View.GONE
                pdfDocument.visibility = View.GONE
            } else {
                layoutDocument.visibility = View.VISIBLE
                documentLine.visibility = View.VISIBLE
                pdfDocument.visibility = View.VISIBLE
            }
            layoutDocument.setOnClickListener {
                if (condition) {
                    arrowDownDocument.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    pdfDocument.visibility = View.VISIBLE
                    condition = !condition
                } else {
                    arrowDownDocument.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    pdfDocument.visibility = View.GONE
                    condition = !condition
                }
            }

        }
    }

    override fun projectInfoViewFail(data: ProjectInfoDetail.ProjectInfoDetailRes) {
        binding.apply {
            progressbarGetData.visibility = View.VISIBLE
            viewBackground.visibility = View.VISIBLE
        }
    }

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.projectInfoView(
                    ProjectInfoDetail.ProjectInfoDetailObject(id),
                    UTSwapApp.instance
                )            }
        }
    }
    private val onclickAdapter: ProjectViewPagerAdapter.OnclickAdapter =
        object : ProjectViewPagerAdapter.OnclickAdapter {
            override fun onClickMe(
                projectInfoSlideImageModel: String,
                position: Int,
                view: View
            ) {
//                val imageSlideDialog: DialogProjectSliderImage = DialogProjectSliderImage.newInstance(
//                    projectInfoSlideImageModel
//                )
//                imageSlideDialog.show(supportFragmentManager, "imageSlideDialog")

                val intent = Intent(this@ProjectInfoActivity, ImageViewActivity::class.java)
                val obj = ViewImageModel.ViewImage()
                obj.gallery = imagesSlider
                obj.position = position
                intent.putExtra("VIEW_IMAGE", Gson().toJson(obj))
                startActivity(
                    intent, ActivityOptions.makeSceneTransitionAnimation(
                        this@ProjectInfoActivity, view, "UT Swap"
                    ).toBundle()
                )
            }

        }

}
