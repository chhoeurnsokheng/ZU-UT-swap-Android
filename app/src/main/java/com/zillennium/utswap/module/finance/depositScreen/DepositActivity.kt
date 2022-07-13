package com.zillennium.utswap.module.finance.depositScreen


import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceDepositBinding
import com.zillennium.utswap.models.DepositModel
import com.zillennium.utswap.module.finance.depositScreen.adapter.DepositAdapter
import com.zillennium.utswap.module.finance.addCardScreen.DepositAddCardActivity
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
    private var depositAdapter: DepositAdapter? = null
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    private var imgCardVisa: Int? = 0
    private var cardTitleVisa: String? = ""

    override fun initView() {
        super.initView()
        try {
            toolBar()

            binding.apply {

                imageCard = arrayOf(
                    R.drawable.aba_pay,
                    R.drawable.visa_mastercard,
                    R.drawable.acleda,
                    R.drawable.sathapana
                )

                cardTitle = arrayOf(
                    "ABA Pay",
                    "Visa/ Master Card",
                    "Acleda Bank",
                    "Sathapana"
                )

                newArrayList = arrayListOf<DepositModel>()
                for (i in imageCard.indices) {
                    val deposit = DepositModel(
                        imageCard[i],
                        cardTitle[i]
                    )
                    newArrayList.add(deposit)
                }
                rvPayment.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                depositAdapter = DepositAdapter(newArrayList, onClickDeposit)
                rvPayment.adapter = depositAdapter


            }

        } catch (error: Exception) {
            // Must be safe
        }
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

    private val onClickDeposit: DepositAdapter.OnClickDeposit = object : DepositAdapter.OnClickDeposit {
        override fun ClickDepositCard(cardTitle: String,cardImg: Int) {

            when (cardTitle) {
                "ABA Pay"-> {
                    val depositDailogPayment = BottomSheetFinanceDepositPayment.newInstance(cardTitle,cardImg)
                    depositDailogPayment.show(this@DepositActivity.supportFragmentManager, "Deposit Dialog")
                }
                "Visa/ Master Card"->{
//                    args.putString("CardTitle","Visa/ Master Card")

                    cardTitleVisa = cardTitle
                    imgCardVisa = cardImg
                    val intent = Intent(this@DepositActivity, DepositAddCardActivity::class.java)
                    startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)

                }
                "Acleda Bank"->{
                    val depositDailogPayment = BottomSheetFinanceDepositPayment.newInstance(cardTitle.toString(),cardImg)
                    depositDailogPayment.show(this@DepositActivity.supportFragmentManager, "Deposit Dialog")

                }
                "Sathapana"->{
                    val depositDailogPayment = BottomSheetFinanceDepositPayment.newInstance(cardTitle.toString(),cardImg)
                    depositDailogPayment.show(this@DepositActivity.supportFragmentManager, "Deposit Dialog")
                }

            }

        }


    }



    ///use it to pop up bottom sheet dialog and save data
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode === RESULT_OK) {
                val depositDailogPayment = BottomSheetFinanceDepositPayment.newInstance(cardTitleVisa.toString(), imgCardVisa)
                depositDailogPayment.show(this@DepositActivity.supportFragmentManager, "Deposit Dialog")
            }
        }
    }

}

