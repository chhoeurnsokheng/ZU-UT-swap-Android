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
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectBinding
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.models.project.ProjectList
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectAdapter
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectGridAdapter
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity


class ProjectActivity :
    BaseMvpActivity<ProjectView.View, ProjectView.Presenter, ActivityProjectBinding>(),
    ProjectView.View {

    override var mPresenter: ProjectView.Presenter = ProjectPresenter()
    override val layoutResource: Int = R.layout.activity_project

    private var projectList: ArrayList<ProjectList.ProjectListData> = arrayListOf()
    private var projectGridAdapter: ProjectGridAdapter? = null
    private var projectAdapter: ProjectAdapter? = null
    private var name = ""
    private var viewGrid = false
    private var sort = " " // asc // desc
    private var sortedDate = true
    private var page = 1
    private var lastPosition = 0
    private var totalItem = 0

    companion object {
        var order_page: Int? = null
    }

    override fun initView() {
        super.initView()
        requestData()
        loadMoreData()
        onSwipeRefresh()
        onSearchBox()
        onChangeLayoutManager()
        mPresenter.getNotificationLists(this)
        SessionVariable.BADGE_NUMBER.observe(this){
            if (SessionVariable.BADGE_NUMBER.value?.isNotEmpty() == true && SessionVariable.BADGE_NUMBER.value != "0") {
                binding.tvBadgeNumber.visibility = View.VISIBLE
                if (it.toInt() > 9) {
                    binding.tvBadgeNumber.text = "9+"
                } else {
                    binding.tvBadgeNumber.text = it
                }
            } else {
                binding.tvBadgeNumber.visibility = View.INVISIBLE
            }
        }
        binding.apply {
            backImage.setOnClickListener {
                finish()
            }

            imgNotification.setOnClickListener {
                if (SessionVariable.SESSION_STATUS.value == true) {
                    val intent = Intent(this@ProjectActivity, NotificationActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@ProjectActivity, SignInActivity::class.java)
                    startActivityForResult(intent, 1111)
                }
            }

            layView.setOnClickListener {
                viewGrid = !viewGrid
                onChangeLayoutManager()
            }
            layView.callOnClick()

            layLast.setOnClickListener {
                projectList.clear()
                sortedDate = !sortedDate
                if (sortedDate) {
                    page = 1
                    sort = "desc"
                    txtSort.text ="Latest"
                    imgLast.rotation = 180f
                    requestData()
                    binding.pgLoading.visibility = View.VISIBLE

                } else {
                    page = 1
                    imgLast.rotation = 0f
                    sort = "asc"
                    txtSort.text="Oldest"
                    requestData()
                    binding.pgLoading.visibility = View.VISIBLE
                }

            }


        }

    }

    override fun projectListSuccess(data: ProjectList.ProjectListRes) {

        binding.projectListSwipeRefresh.isRefreshing = false
        totalItem = data.data?.totalpage ?: 0

        if (page == 1) {
            projectList.clear()
        }

        data.data?.projects?.let { projectList.addAll(it) }

        binding.apply {
            rvProject.adapter?.notifyDataSetChanged()
            pgLoading.visibility = View.GONE
            progressBar.visibility = View.GONE
        }

    }



    override fun projectListFail(data: ProjectList.ProjectListRes) {
        binding.apply {
            pgLoading.visibility = View.GONE
            projectListSwipeRefresh.isRefreshing = false
        }
    }

    override fun onNotificationSuccess(data: NotificationModel.NotificationData) {
        if (data.countGroupNoti?.isNotEmpty() == true || data.countGroupNoti == "0" ) {
            binding.tvBadgeNumber.visibility = View.VISIBLE
            if ((data.countGroupNoti?.toInt() ?: 0) > 9) {
                binding.tvBadgeNumber.text = "9+"
            } else {
                binding.tvBadgeNumber.text = data.countGroupNoti
            }
        } else {
            binding.tvBadgeNumber.visibility = View.INVISIBLE
        }

    }

    override fun onNotificationFail(data: NotificationModel.NotificationRes) {
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
                    name = char.toString()
                    page = 1
                    requestData()
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

            icSearch.setOnClickListener {
                linearLayoutSearch.visibility = View.VISIBLE
                etSearch.requestFocus()
            }

            txtCancel.setOnClickListener {
                linearLayoutSearch.visibility = View.GONE
                etSearch.text.clear()
                val inputMethodManager = UTSwapApp.instance.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(etSearch.windowToken, 0)
            }

        }
    }


    private fun loadMoreData() {
        binding.rvProject.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    lastPosition = (binding.rvProject.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastPosition == projectList.size - 1 && page < totalItem) {
                        binding.progressBar.visibility = View.VISIBLE
                        page++
                        requestData()
                    }

                }
            }
        })
    }

    private fun requestData() {
        val bodyObj = ProjectList.ProjectListBody()
        bodyObj.page = page
        bodyObj.sort = sort
        bodyObj.name = name
        mPresenter.projectList(bodyObj)
    }

    private fun onChangeLayoutManager() {

        binding.apply {
            if (viewGrid) {
                page = 1
                requestData()
                viewType.setImageResource(R.drawable.ic_grid_view)
                rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                projectGridAdapter = ProjectGridAdapter(projectList)
                rvProject.adapter = projectGridAdapter

            } else {
                page = 1
                requestData()
                viewType.setImageResource(R.drawable.ic_list_view)
                rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                projectAdapter = ProjectAdapter(projectList)
                rvProject.adapter = projectAdapter

            }
        }

    }

    private fun onSwipeRefresh() {
        binding.apply {
            projectListSwipeRefresh.setOnRefreshListener {
                page = 1
                requestData()
                mPresenter.getNotificationLists(this@ProjectActivity)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            mPresenter.getNotificationLists(this)
        }
    }



}









