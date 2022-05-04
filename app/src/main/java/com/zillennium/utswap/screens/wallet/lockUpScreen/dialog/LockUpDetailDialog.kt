package com.zillennium.utswap.screens.wallet.lockUpScreen.dialog


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


class LockUpDetailDialog : DialogFragment() {
    internal var view: View? = null
    private var txtAmount: TextView? = null
    private var txtDate: TextView? = null
    private var txtProject: TextView? = null
    private var txtPeriod: TextView? = null
    private var txtMaturity: TextView? = null
    private var img: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_wallet_lock_up_history, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        initAction()
        return view
    }

    fun initView() {
        img = view?.findViewById(R.id.img_close)
        txtAmount = view?.findViewById(R.id.txt_amount_dialog)
        txtDate = view?.findViewById(R.id.txt_date_dialog)
        txtMaturity = view?.findViewById(R.id.txt_maturity_date_dialog)
        txtProject = view?.findViewById(R.id.txt_project_dialog)
        txtPeriod = view?.findViewById(R.id.txt_lock_period_dialog)
    }

    fun initAction() {
        img!!.setOnClickListener { dismiss() }
        txtAmount!!.text = arguments?.getString("amount")
        txtDate!!.text = arguments?.getString("date")
        txtMaturity!!.text = arguments?.getString("maturity")
        txtProject!!.text = arguments?.getString("project")
        txtPeriod!!.text = arguments?.getString("period")
    }

    companion object {
        fun newInstance(
            amount: String?,
            date: String?,
            project: String?,
            period: String?,
            maturity: String?
        ): LockUpDetailDialog {
            val lockUpDetailDialog = LockUpDetailDialog()
            val args = Bundle()
            args.putString("amount", amount)
            args.putString("date", date)
            args.putString("project", project)
            args.putString("period", period)
            args.putString("maturity", maturity)
            lockUpDetailDialog.arguments = args
            return lockUpDetailDialog
        }
    }
}
