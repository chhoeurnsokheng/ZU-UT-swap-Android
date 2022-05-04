package com.zillennium.utswap.screens.wallet.historicalScreen.dialog


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R


class HistoricalDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var img: ImageView? = null
    private var txtName: TextView? = null
    private var txtDate: TextView? = null
    private var txtVolume: TextView? = null
    private var txtPrice: TextView? = null
    private var txtSettlement: TextView? = null
    private var txtUtBalance: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_historical_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        img = view?.findViewById(R.id.img_close)
        txtName = view?.findViewById(R.id.txt_name_ut)
        txtDate = view?.findViewById(R.id.txt_date_dialog)
        txtVolume = view?.findViewById(R.id.txt_volume_dialog)
        txtPrice = view?.findViewById(R.id.txt_price_dialog)
        txtSettlement = view?.findViewById(R.id.txt_settlement_dialog)
        txtUtBalance = view?.findViewById(R.id.txt_ut_balance_dialog)
    }

    fun initAction() {
        img!!.setOnClickListener { dismiss() }
        txtName!!.text = arguments?.getString("nameSub")
        txtDate!!.text = arguments?.getString("date")
        txtVolume!!.text = arguments?.getString("volume")
        txtPrice!!.text = arguments?.getString("price")
        txtSettlement!!.text = arguments?.getString("settlement")
        txtUtBalance!!.text = arguments?.getString("utBalance")
    }

    companion object {
        fun newInstance(
            nameSub: String?,
            date: String?,
            value: String?,
            volume: String?,
            price: String?,
            settlement: String?,
            utBalance: String?
        ): HistoricalDetailDialog {
            val historicalDetailDialog = HistoricalDetailDialog()
            val args = Bundle()
            args.putString("nameSub", nameSub)
            args.putString("date", date)
            args.putString("volume", volume)
            args.putString("price", price)
            args.putString("settlement", settlement)
            args.putString("utBalance", utBalance)
            historicalDetailDialog.arguments = args
            return historicalDetailDialog
        }
    }
}
