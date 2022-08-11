package com.zillennium.utswap.module.project.projectScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectBinding
import com.zillennium.utswap.models.project.ProjectList
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectAdapter
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectGridAdapter
import com.zillennium.utswap.module.system.notification.NotificationActivity


class ProjectActivity : BaseMvpActivity<ProjectView.View, ProjectView.Presenter, ActivityProjectBinding>(),
    ProjectView.View {

    override var mPresenter: ProjectView.Presenter = ProjectPresenter()
    override val layoutResource: Int = R.layout.activity_project

    private var projectList: ArrayList<ProjectList.ProjectListData> = arrayListOf()
    private var projectGridAdapter: ProjectGridAdapter? = null
    private var projectAdapter: ProjectAdapter? = null
    private var name = ""
    private var viewGrid = false
    private var sort = "desc"
    private var sortedDate = true
    private var page: Int? = 1
    private var lastPosition = 0
    private var totalItem = ""

    override fun initView() {
        super.initView()
        mPresenter.projectList(ProjectList.ProjectListObject("", page, ""))


        binding.apply {
            backImage.setOnClickListener {
                finish()
            }

            //Notification message
            imgNotification.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                startActivity(intent)
            }

            /* Change View on click */
            layView.setOnClickListener {
                viewGrid = !viewGrid
                onChangeLayoutManager()
            }
            layView.callOnClick()

            /* Sorted on click */
            layLast.setOnClickListener {
                projectList.clear()
                sortedDate = !sortedDate
                if (sortedDate) {
                    imgLast.rotation = 180f
                    mPresenter.projectList(
                        ProjectList.ProjectListObject("", page, "desc"))

                } else {
                    imgLast.rotation = 0f
                    mPresenter.projectList(
                        ProjectList.ProjectListObject("", page, "asc"),

                    )
                }

            }

            onCallApi()
            onSearchBox()
            projectLoadingRefresh()
            onClickReadMore()
        }

    }

    override fun projectListSuccess(data: ProjectList.ProjectListRes) {

        binding.apply {
                pgLoading.visibility = View.GONE
               progressBarReadMore.visibility = View.GONE
               layProjectLoading.visibility = View.VISIBLE
            projectListSwipeRefresh.isRefreshing = false

            if (data.data != null) {
                data.data?.projects?.let { projectList.addAll(it) }

                if (viewGrid) {
                    rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                    projectGridAdapter =
                        ProjectGridAdapter(object : ProjectGridAdapter.OnClickGridProject {
                            override fun onClickMe(id: String) {
                                onclickProjectDetail(id)
                            }

                        })
                    rvProject.adapter = projectGridAdapter
                    projectGridAdapter?.items = projectList
                } else {
                    rvProject.adapter?.notifyDataSetChanged()
                    rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    projectAdapter = ProjectAdapter(object : ProjectAdapter.OnclickProject {
                        override fun onClickMe(id: String) {
                            onclickProject(id)
                        }

                    })
                    rvProject.adapter = projectAdapter
                    projectAdapter?.items = projectList
                }


                //Add more data page
                page = page!! + 1
                txtReadMore.visibility = View.VISIBLE
                txtLoading.visibility = View.GONE
            } else {
                layProjectLoading.visibility = View.GONE
                txtEndData.visibility = View.VISIBLE
            }
        }

    }



    override fun projectListFail(data: ProjectList.ProjectListRes) {
        binding.apply {
            pgLoading.visibility = View.GONE
            projectListSwipeRefresh.isRefreshing = false
        }
    }

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.projectList(
                    ProjectList.ProjectListObject(name, page, sort),

                )
            }
        }
    }

    private fun projectLoadingRefresh() {
        binding.apply {
            // Swipe refresh to get page
            projectListSwipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.primary
                )
            )

            projectListSwipeRefresh.setOnRefreshListener {
                txtEndData.visibility = View.GONE
                page = 1
                projectList.clear()
                mPresenter.projectList(
                    ProjectList.ProjectListObject(name, page, sort),

                )
            }
        }
    }

    private fun onClickReadMore() {
        binding.apply {
            readMoreProject.setOnClickListener {
                txtReadMore.visibility = View.GONE
                txtLoading.visibility = View.VISIBLE
                progressBarReadMore.visibility = View.VISIBLE
            mPresenter.projectList(ProjectList.ProjectListObject(name, page, sort))
        }
            }
        }
//        binding.rvProject.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    lastPosition =
//                        (binding.rvProject.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//                    if (lastPosition == projectList.size - 1 && projectList.size < totalItem.toInt()) {
//                        binding.progressBarReadMore.visibility = View.VISIBLE
//                        page = 1
//
//                    }
//
//                }
//            }
//        })




    private fun onSearchBox() {

        binding.apply {
            etSearch.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    laySearch.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.primary
                            )
                        )
                } else {
                    laySearch.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.light_gray
                            )
                        )
                }
            }

            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    //getDataSearch(etSearch.text.toString())
                    projectList.clear()
                    mPresenter.projectList(
                        ProjectList.ProjectListObject(
                            etSearch.text.toString(),
                            1,
                            ""
                        )
                    )
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

            icSearch.setOnClickListener {
                linearLayoutSearch.visibility = View.VISIBLE
            }

            txtCancel.setOnClickListener {
                linearLayoutSearch.visibility = View.GONE
                etSearch.text.clear()
                val inputMethodManager =
                    UTSwapApp.instance.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(etSearch.windowToken, 0)
            }

        }
    }






    private fun onChangeLayoutManager() {
        binding.apply {
            if (viewGrid) {
                projectGridAdapter?.notifyDataSetChanged()
                viewType.setImageResource(R.drawable.ic_grid_view)
                rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                projectGridAdapter?.items = projectList
                rvProject.adapter = projectGridAdapter

            } else {
                projectAdapter?.notifyDataSetChanged()
                viewType.setImageResource(R.drawable.ic_list_view)
                rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                projectAdapter = ProjectAdapter(object : ProjectAdapter.OnclickProject {
                    override fun onClickMe(id: String) {
                        onclickProject(id)
                    }
                })
                projectAdapter?.items = projectList
                rvProject.adapter = projectAdapter

            }
        }

    }

    private fun onclickProjectDetail(id: String = "") {
        ProjectInfoActivity.launchProjectInfoActivity(this,id)
//        if (id != "") {
//            val intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
//            intent.putExtra("id", id)
//            ContextCompat.startActivity(intent)
//        }
    }

    private fun onclickProject(id: String = "") {
        ProjectInfoActivity.launchProjectInfoActivity(this,id)
//        if (id != "") {
//            val intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
//            intent.putExtra("id", id)
//            ContextCompat.startActivity(intent)
//        }
    }
}









