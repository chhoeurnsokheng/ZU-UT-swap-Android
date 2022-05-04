package com.zillennium.utswap.screens.wallet.depositScreen

import android.view.View
import android.widget.AdapterView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletDepositBinding
import com.zillennium.utswap.models.PaymentMethod.CustomItem
import com.zillennium.utswap.screens.wallet.depositScreen.adapter.CustomAdapter
import java.io.IOException

class DepositActivity :
    BaseMvpActivity<DepositView.View, DepositView.Presenter, ActivityWalletDepositBinding>(),
    DepositView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: DepositView.Presenter = DepositPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_deposit

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val customList = getCustomList()
                val adapter = CustomAdapter(UTSwapApp.instance, customList)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this@DepositActivity

                imgBack.setOnClickListener { finish() }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun getCustomList(): ArrayList<CustomItem> {
        val customList = ArrayList<CustomItem>()
        customList.add(CustomItem("Choose your Payment Method", R.drawable.ic_baseline_payment_24))
        customList.add(CustomItem("Visa/Master Card", R.drawable.visa_card_logo))
        customList.add(CustomItem("ABA PAY", R.drawable.aba_logo))
        customList.add(CustomItem("ACLEDA Bank", R.drawable.acleda_logo))
        return customList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
        val item = adapterView.selectedItem as CustomItem
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}
