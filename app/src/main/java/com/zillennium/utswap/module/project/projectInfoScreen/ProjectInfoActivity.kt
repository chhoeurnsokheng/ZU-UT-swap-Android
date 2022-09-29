package com.zillennium.utswap.module.project.projectInfoScreen

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.androidstudy.networkmanager.Tovuti
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectInfoBinding
import com.zillennium.utswap.models.ProjectInfoDetailModel
import com.zillennium.utswap.models.ViewImageModel
import com.zillennium.utswap.models.project.ProjectInfoDetail
import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.module.project.ViewImage.ImageViewActivity
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectViewPagerAdapter
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity
import com.zillennium.utswap.utils.*


class ProjectInfoActivity :
    BaseMvpActivity<ProjectInfoView.View, ProjectInfoView.Presenter, ActivityProjectInfoBinding>(),
    ProjectInfoView.View {

    override var mPresenter: ProjectInfoView.Presenter = ProjectInfoPresenter()
    override val layoutResource: Int = R.layout.activity_project_info
    private var projectName = ""
    private var termCondition = true
    private var condition = true
    private var imagesSlider: ArrayList<String> = arrayListOf()
    private var id: Int = 0
    private var project_Id :Int? = 0
    private var project_Name:String = ""
    private var project_Action  = false

    companion object {
        fun launchProjectInfoActivity(context: Context, id: String?) {
            val intent = Intent(context, ProjectInfoActivity::class.java)
            intent.putExtra(Constants.Project.Project_Id, id)
//            intent.putExtra(Constants.Project.ProjectName, projectName)
            context.startActivity(intent)
        }
    }

    override fun initView() {

        super.initView()

        if (intent.hasExtra(Constants.Project.Project_Id)) {
            id = intent.extras?.getString(Constants.Project.Project_Id)?.toInt() ?: 0

            id.let { ProjectInfoDetail.ProjectInfoDetailObject(it) }.let {
                mPresenter.projectInfoView(it, UTSwapApp.instance)
            }

        }





        binding.apply {
            onCallApi()
            btnBack.setOnClickListener {
                onBackPressed()
            }



        }
    }

    override fun projectInfoViewSuccess(data: ProjectInfoDetail.ProjectInfoDetailData) {
        val DECIMAL_FORMAT = "###,###.##"


        mPresenter.checkProjectStatus(this, ProjectInfoDetail.ProjectTerCondition(id))


        binding.apply {
            txtDetailTitle.text = data.project_name.toString()
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
            var x = data.land_size?.substring(0, data.land_size!!.length - 4)
            var basePrice = data.base_price?.let { UtilKt().formatValue(it, "###,###.##") }
            var targetPrice = data.target_price?.let { UtilKt().formatValue(it, "###,###.##") }

            val valueInfo = arrayOf(
                data.title_deed,
                "${groupingSeparatorInt(x?.toInt() ?: 0)} sqm",
                "${groupingSeparatorInt(data.total_ut!!.toInt())} UT",
                "$ ${basePrice}/sqm",
                "$ ${targetPrice}/sqm",
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
            layoutBasePrice.apply {
                layoutInvestment.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.gray_D9D9D9
                    )
                )
                txtPerUt.text = data.investment_information?.base?.base_ut_price?.let {
                    UtilKt().formatValue(
                        it, DECIMAL_FORMAT
                    )
                }
                txtPerUt.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.black_0A0B12
                        )
                    )
                )
                icDollar.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.black_0A0B12
                        )
                    )
                )

                txtValueUt.text = data.investment_information?.base?.value?.let {
                    UtilKt().formatValue(
                        it, DECIMAL_FORMAT
                    )
                }
                txtValueUt.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.black_0A0B12
                        )
                    )
                )

                txtSqmUt.text = data.investment_information?.base?.sqm?.let {
                    UtilKt().formatValue(
                        it, DECIMAL_FORMAT
                    )
                }
                txtSqmUt.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.black_0A0B12
                        )
                    )
                )
                icDollar2.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.black_0A0B12
                        )
                    )
                )

            }
            layoutTargetPrice.apply {
                layoutInvestment.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.secondary
                    )
                )
                txtPerUt.text = data.investment_information?.target?.future_ut_price?.let {
                    UtilKt().formatValue(
                        it, DECIMAL_FORMAT
                    )
                }
                txtPerUt.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.write_ECECEC
                        )
                    )
                )
                icDollar.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.write_ECECEC
                        )
                    )
                )
                txtValueUt.text = data.investment_information?.target?.value?.let {
                    UtilKt().formatValue(
                        it, DECIMAL_FORMAT
                    )
                }
                txtValueUt.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.write_ECECEC
                        )
                    )
                )
                icDollar2.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.write_ECECEC
                        )
                    )
                )
                txtSqmUt.text = data.investment_information?.target?.sqm?.let {
                    UtilKt().formatValue(
                        it, DECIMAL_FORMAT
                    )
                }
                txtSqmUt.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.write_ECECEC
                        )
                    )
                )

            }

            /* expected return */
            txtExpectedReturn.text = "${data.expected_return}%"

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
            layoutDocument.setOnClickListener {
                if (condition) {
                    arrowDownDocument.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    pdfDocument.visibility = View.VISIBLE
                    txtUpcoming.visibility = View.VISIBLE
                    condition = !condition
                } else {
                    arrowDownDocument.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    pdfDocument.visibility = View.GONE
                    txtUpcoming.visibility = View.GONE
                    condition = !condition
                }
            }

            project_Name = data.project_name.toString()
            project_Id = data.id?.toInt()

            if (data.action == "Subscribe") {
                btnTrade.visibility = View.GONE
                btnSubscript.visibility = View.VISIBLE
                btnUpcoming.visibility = View.GONE
                project_Action = true

//                btnSubscript.setOnClickListener {
//                    SubscriptionActivity.launchSubscriptionActivity(this@ProjectInfoActivity,
//                        data.id,
//                        data.project_name.toString()
//                    )
//                }


            }
            if (data.action == "Trade") {
                btnTrade.visibility = View.VISIBLE
                btnSubscript.visibility = View.GONE
                btnUpcoming.visibility = View.GONE
                btnTrade.setOnClickListener {
                    TradeExchangeActivity.launchTradeExchangeActivityFromProjectDetail(this@ProjectInfoActivity, data.project_name.toString(),data.market_name.toString(),data.id.toString(),data.market_id.toString())
                }


            }
            if (data.action == "Upcomming") {
                btnTrade.visibility = View.GONE
                btnSubscript.visibility = View.GONE
                btnUpcoming.visibility = View.VISIBLE
            }

            if(data.market_id.isNullOrEmpty() || data.market_name.isNullOrEmpty()){
                btnTrade.visibility = View.GONE
                btnSubscript.visibility = View.VISIBLE
                btnUpcoming.visibility = View.GONE
                btnSubscript.setOnClickListener {
                    SubscriptionActivity.launchSubscriptionActivity(this@ProjectInfoActivity,
                        data.id,
                        data.project_name.toString()
                    )
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

    override fun subscriptionProjectTermConditionSuccess(data: SubscriptionProject.SubScribeTermCondition) {}

    override fun subscriptionProjectTermConditionFailed(data: String) {}

    override fun checkProjectStatusSuccess(data: SubscriptionProject.SubScribeTermCondition) {
        binding.apply {
            if (data.status ==1){
                if ( project_Action == true){
                    btnSubscript.setOnClickListener {
                        SubscriptionActivity.launchSubscriptionActivity(this@ProjectInfoActivity,
                            project_Id.toString(),
                            project_Name
                        )
                    }
                }
            }
            if (data.status ==0){
                btnSubscript.setOnClickListener {
                    DialogUtiSubscribe().customDialog(
                        R.drawable.ic_vserion_icon,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in re","Terms and Conditions",true, "Agree",object : DialogUtil.OnAlertDialogClick {
                            override fun onLabelCancelClick() {
                                mPresenter.subscriptionProjectTermCondition(this@ProjectInfoActivity,id)

                                SubscriptionActivity.launchSubscriptionActivity(this@ProjectInfoActivity,
                                    project_Id.toString(),
                                    project_Name
                                )

                            }

                        },this@ProjectInfoActivity
                    )

                }
            }

        }
    }

    override fun checkProjectStatusFailed(data: String) {}

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.projectInfoView(
                    ProjectInfoDetail.ProjectInfoDetailObject(id),
                    UTSwapApp.instance
                )
            }
        }
    }

    private val onclickAdapter: ProjectViewPagerAdapter.OnclickAdapter =
        object : ProjectViewPagerAdapter.OnclickAdapter {
            override fun onClickMe(
                projectInfoSlideImageModel: String,
                position: Int,
                view: View
            ) {

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
