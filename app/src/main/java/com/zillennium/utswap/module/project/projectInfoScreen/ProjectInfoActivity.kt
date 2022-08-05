package com.zillennium.utswap.module.project.projectInfoScreen

import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectInfoBinding
import com.zillennium.utswap.models.ProjectInfoDetailModel
import com.zillennium.utswap.models.ProjectInfoInvestmentModel
import com.zillennium.utswap.models.ProjectInfoSlideImageModel
import com.zillennium.utswap.models.ViewImageModel
import com.zillennium.utswap.module.project.ViewImage.ImageViewActivity
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectInfoInvestmentAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.adapter.ProjectViewPagerAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.dialog.DialogProjectSliderImage
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity


class ProjectInfoActivity :
    BaseMvpActivity<ProjectInfoView.View, ProjectInfoView.Presenter, ActivityProjectInfoBinding>(),
    ProjectInfoView.View {

    override var mPresenter: ProjectInfoView.Presenter = ProjectInfoPresenter()
    override val layoutResource: Int = R.layout.activity_project_info

    private var termCondition = true
    private var condition = true
    private var imagesSlider: ArrayList<String> = arrayListOf()

    override fun initView() {

        super.initView()
        try {
            binding.apply {

                btnBack.setOnClickListener {
                    onBackPressed()
                }

                btnSubscriptTrade.setOnClickListener {
                    val intent : Intent = Intent(UTSwapApp.instance, SubscriptionActivity::class.java)
                    startActivity(intent)
                }




                /* Image Slider with View Pager and TabLayout*/
                 imagesSlider = arrayListOf(
                    "https://utswap.io/Upload/issue/624baccd65299.png",
                    "https://utswap.io/Upload/issue/624bacd53d783.jpg",
                    "https://utswap.io/Upload/issue/624baceb728a8.png",
                    "https://utswap.io/Upload/issue/624baced5d6a8.jpg"
                )

                val projectInfoSlideImage = arrayListOf<ProjectInfoSlideImageModel>()

                for (i in imagesSlider.indices){
                    val projectImage = ProjectInfoSlideImageModel(
                        imagesSlider[i],
                    )
                    projectInfoSlideImage.add(projectImage)
                }

                val adapter = ProjectViewPagerAdapter(projectInfoSlideImage, onclickAdapter)
                imageSlideViewPager.adapter = adapter

                TabLayoutMediator(tabLayoutDot, imageSlideViewPager) { tab, position ->

//                    imageDialog.setOnClickListener {
//                        val imagesDialog: DialogProjectSliderImage =
//                            DialogProjectSliderImage.newInstance(
//                                imagesSlider[tab.position]
//                            )
//                        imagesDialog.show(
//                            requireActivity().supportFragmentManager,
//                            "balanceHistoryDetailDialog"
//                        )
//                    }
                }.attach()

                imageSlideViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){})



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
                    "Hard Title (10)",
                    "384 761 sqm",
                    "220 000 UT",
                    "$1 625/sqm",
                    "$1 804/sqm",
                    "Focus Property Co., Ltd.",
                    "St. 105k, Kbal Damrei 1, Kakab 1, Pur Senchey, Phnom Penh"
                )

                val projectInfoDetailArrayList = arrayListOf<ProjectInfoDetailModel>()

                for (i in titleInfoDetail.indices) {
                    val projectInfo = ProjectInfoDetailModel(
                        titleInfoDetail[i],
                        valueInfo[i]
                    )
                    projectInfoDetailArrayList.add(projectInfo)
                }
//
                rvProjectInfoDetail.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvProjectInfoDetail.adapter = ProjectInfoDetailsAdapter(projectInfoDetailArrayList)


                /* Recycle Investment info */
                val perUT = arrayOf(

                    "4.10",
                    "4.55",
                )
                val valueUT = arrayOf(
                    "902 000",
                    "1 001 000",
                )
                val sqmUT = arrayOf(
                    "1 625",
                    "1 804",
                )

                val projectInfoInvestmentArrayList = arrayListOf<ProjectInfoInvestmentModel>()

                for (i in perUT.indices) {
                    val projectInvestment = ProjectInfoInvestmentModel(
                        perUT[i],
                        valueUT[i],
                        sqmUT[i]
                    )
                    projectInfoInvestmentArrayList.add(projectInvestment)
                }

                rvProjectInvestmentInfo.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvProjectInvestmentInfo.adapter =
                    ProjectInfoInvestmentAdapter(projectInfoInvestmentArrayList)


                /* Term and condition && Document */
                txtTermCondition.text =
                    "1. Locked-up period of 45 days is applicable on all UTS bought on discount. " +
                            "\n2. NO guaranteed rate of return on investment; Rate of return is variable depending on the deal closing price. " +
                            "\n3. NO guarantee on the duration to when the deal can be closed. \n4. NO contract for the sale and purchase of UT. " +
                            "The transaction report testifies your economic benefit. \n5. Right to the decision on the sale of land belongs to Trading Committee. " +
                            "The SPA will testify the selling price to the third party. This price will be the delisting price of all UT. " +
                            "\n6. UT will be sold and bought back via digital trading platform only. Once 80% of the UT are collected by the company, " +
                            "the remaining 20% will be automatically exchanged at the delisting price. \n7. Trading is open 7/7 on UTSWAP Platform from 9 am 4 pm. " +
                            "\n8. The transaction fee per transaction is 0.3% of traded value. \n9. Minimum investment is 1 UT. \n10. Failure to comply with our terms " +
                            "and conditions results in penalty of 100% of the violated transaction value in addition to account freezing."
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
                layGoogleMap.setOnClickListener {
                    val url = "https://goo.gl/maps/m4mXAaFMHT2SecpC7"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onclickAdapter: ProjectViewPagerAdapter.OnclickAdapter = object: ProjectViewPagerAdapter.OnclickAdapter{
        override fun onClickMe(projectInfoSlideImageModel: ProjectInfoSlideImageModel, position: Int, view: View) {
            /*val imageSlideDialog: DialogProjectSliderImage = DialogProjectSliderImage.newInstance(
                projectInfoSlideImageModel?.imageSlider
            )
            imageSlideDialog.show(supportFragmentManager, "imageSlideDialog")*/

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
