package com.zillennium.utswap.bases.mvp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> :
    Fragment(), BaseMvpView {
    protected abstract var mPresenter: T
    protected val DISMISS_TIMEOUT = 500
    val tagId = UUID.randomUUID().toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
        mPresenter.initViewPresenter(requireActivity().baseContext, savedInstanceState)
    }

    override fun getContext(): Context? {
        if (activity != null) {
            return requireActivity().baseContext
        }
        return null
    }

    override fun initView() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onNext(page: Int) {

    }

    override fun onWillBeHidden() {

    }

    override fun onWillBeDisplayed() {

    }

    override fun onRefresh() {

    }

    override fun onSuccess(any: Any) {

    }

    override fun onFail(any: Any) {

    }

    override fun onSetThem(): Int {
        return -1
    }

    override fun onLoading() {

    }

    override fun showError(error: String) {

    }

    protected fun navigationRoute(action: NavDirections) {
        try {
            findNavController().navigate(action)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun popFragmentNavigation() {
        findNavController().popBackStack()
    }


}

