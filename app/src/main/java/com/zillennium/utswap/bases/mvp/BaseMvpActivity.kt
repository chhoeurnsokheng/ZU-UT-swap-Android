package com.zillennium.utswap.bases.mvp

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zillennium.utswap.UTSwapApp

abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>, M : ViewDataBinding>
    : AppCompatActivity(), BaseMvpView {

    protected lateinit var binding: M
    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (onSetThem() != -1) {
            setTheme(onSetThem())
        }
        this.mPresenter.attachView(this@BaseMvpActivity as V)
        this.binding = DataBindingUtil.setContentView(this, layoutResource)
        this.mPresenter.initViewPresenter(this, savedInstanceState)
    }

    override fun getContext(): Context = this

    override fun initView() {

    }

    protected abstract var mPresenter: T

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
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

    override fun onLoading() {

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
//        Runtime.getRuntime().gc()
//        System.gc()
    }

    override fun onSetThem(): Int {
        return -1
    }
}