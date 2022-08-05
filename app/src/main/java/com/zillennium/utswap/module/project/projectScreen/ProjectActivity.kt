package com.zillennium.utswap.module.project.projectScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectBinding
import com.zillennium.utswap.models.projectList.ProjectList
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectAdapter
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectGridAdapter
import com.zillennium.utswap.module.system.notification.NotificationActivity


class ProjectActivity :
    BaseMvpActivity<ProjectView.View, ProjectView.Presenter, ActivityProjectBinding>(),
    ProjectView.View {

    override var mPresenter: ProjectView.Presenter = ProjectPresenter()
    override val layoutResource: Int = R.layout.activity_project

    private var projectList: ArrayList<ProjectList.ProjectListData> = arrayListOf()
    private var projectGridAdapter: ProjectGridAdapter? = null
    private var projectAdapter: ProjectAdapter? = null
    private var search = ""
    private var viewGrid = false
    private var sortedDate = true
    private var page: Int? = 1


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun initView() {
        super.initView()

        mPresenter.projectList(name = "NR5", page = 1, search = "", sortedDate = true)

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
                sortedDate = !sortedDate
                if (sortedDate) {
                    imgLast.rotation = 180f
                } else {
                    imgLast.rotation = 0f
                }
            }

            onCallApi()
            onSearchBox()
            projectLoadingRefresh()
            onClickReadMore()
        }

    }

    override fun projectListSuccess(data: ArrayList<ProjectList.ProjectListData>) {
        binding.apply {
            pgLoading.visibility = View.GONE
            progressBarReadMore.visibility = View.GONE
            layProjectLoading.visibility = View.VISIBLE
            projectListSwipeRefresh.isRefreshing = false

            if (data.isNotEmpty()) {
                projectList.addAll(data)

                if (viewGrid) {
                    rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
//                    var listenerProject = ProjectGridAdapter(onclickProjectGrid = object = ProjectGridAdapter.
//                    {
////                        override fun onClickMe(id: String) {
////                            onclickProjectDetail(id)
////                        }
//
//                    })
////                    var listProjectGrid = ProjectGridAdapter(onclickProjectGrid = object : ProjectGridAdapter.OnclickProjectGrid{
////
////                    })
                    rvProject.adapter = projectAdapter
                    projectGridAdapter?.items = projectList
                } else {
                    rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance,)
                    rvProject.adapter = projectAdapter
                    projectGridAdapter?.items = projectList

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
            pgLoading.visibility = View.VISIBLE
            projectListSwipeRefresh.isRefreshing = false
        }
    }


    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.projectList(name = "NR5", page = 1, search = "", sortedDate = true)
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
                mPresenter.projectList(name = "NR5", page = 1, search = "", sortedDate = true)
            }
        }
    }

    private fun onClickReadMore() {
        binding.apply {
            readMoreProject.setOnClickListener {
                txtReadMore.visibility = View.GONE
                txtLoading.visibility = View.VISIBLE
                progressBarReadMore.visibility = View.VISIBLE
                mPresenter.projectList(name = "NR5", page = 1, search = "", sortedDate = true)

            }
        }
    }

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
                    search = char.toString()
                }

                override fun afterTextChanged(p0: Editable?) {
//                    getDataSearch()
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
                viewType.setImageResource(R.drawable.ic_grid_view)
                rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                rvProject.adapter = projectGridAdapter
                projectGridAdapter?.items = projectList
            } else {
                viewType.setImageResource(R.drawable.ic_list_view)
                rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvProject.adapter = projectAdapter
                projectAdapter?.items = projectList
            }
        }

    }


    private fun getDataSearch() {
        binding.apply {

            var dataProject: ArrayList<ProjectList.ProjectListData> = arrayListOf()

            if (search.isNotEmpty()) {
                dataProject.clear()
                projectList.map {
                    if (it.project_name?.contains(search, ignoreCase = true) == true) {
                        dataProject.add(it)
                    }
                }
            } else {
                dataProject = projectList
            }

        }

    }

    private fun onclickProjectDetail(id: String = ""){
        if(id != ""){
            val intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

//    private var onclickProject: ProjectAdapter.OnclickProject =
//        object : ProjectAdapter.OnclickProject {
//            override fun onClickMe(id: String) {
//                val intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
//                intent.putExtra("id", id)
//                startActivity(intent)
//            }
//
//
//        }
}
