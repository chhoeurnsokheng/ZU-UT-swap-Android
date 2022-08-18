package com.zillennium.utswap.module.finance.depositScreen


import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceDepositBinding
import com.zillennium.utswap.models.DepositModel
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.depositScreen.adapter.DepositAdapter
import com.zillennium.utswap.module.finance.depositScreen.depositBottomSheet.BottomSheetFinanceDepositPayment

class DepositActivity :
    BaseMvpActivity<DepositView.View, DepositView.Presenter, ActivityFinanceDepositBinding>(),
    DepositView.View {

    override var mPresenter: DepositView.Presenter = DepositPresenter()
    override val layoutResource: Int = R.layout.activity_finance_deposit

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<DepositModel>
    lateinit var imageCard: Array<Int>
    lateinit var cardTitle: Array<String>
    private var listBank: List<DepositObj.DataListRes>? = null
    private var depositAdapter: DepositAdapter? = null
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    private var imgCardVisa: String? = ""
    private var cardTitleVisa: String? = ""
    private var typeOfCard: String? = ""

    override fun initView() {
        super.initView()
        try {
            toolBar()
            mPresenter.onGetListBank(this)
            binding.apply {
                progressBar.visibility = View.VISIBLE
            }

        } catch (error: Exception) {

        }
    }

    override fun onGetListBankSuccess(data: DepositObj.DepositRes) {
        listBank = data.data
        binding.rvPayment.apply {
            adapter = data.data?.let { DepositAdapter(it, onClickDeposit) }
            layoutManager =
                LinearLayoutManager(this@DepositActivity, LinearLayoutManager.VERTICAL, false)
        }
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    override fun onGetListBankFailed(message: String) {
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    override fun onDepositBalanceSuccess(data: DepositObj.DepositReturn) {

    }

    override fun onDepositBalanceFailed(message: String) {

    }

    override fun onGetDepositTransferBalanceLogSuccess(data: DepositObj.DepositRes) {

    }

    override fun onGetDepositTransferBalanceLogFailed(message: String) {

    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.deposit)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private val onClickDeposit: DepositAdapter.OnClickDeposit =
        object : DepositAdapter.OnClickDeposit {
            override fun ClickDepositCard(cardTitle: String?, cardImg: String?, type: String?) {
                imgCardVisa= cardImg
                typeOfCard= type
                val depositDailogPayment = BottomSheetFinanceDepositPayment.newInstance(cardTitle, cardImg,type)
                depositDailogPayment.show(this@DepositActivity.supportFragmentManager, "Deposit Dialog")
//

            }


        }


    ///use it to pop up bottom sheet dialog and save data
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode === RESULT_OK) {
                val depositDailogPayment = BottomSheetFinanceDepositPayment.newInstance(
                    cardTitleVisa.toString(),
                    imgCardVisa.toString(),
                    typeOfCard.toString()
                )
                depositDailogPayment.show(
                    this@DepositActivity.supportFragmentManager,
                    "Deposit Dialog"
                )

            }
        }
    }

}

