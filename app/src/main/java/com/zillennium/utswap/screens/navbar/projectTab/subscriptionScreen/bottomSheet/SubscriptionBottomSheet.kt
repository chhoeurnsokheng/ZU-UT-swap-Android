package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.dialog.SubscriptionConfirmDialog


class SubscriptionBottomSheet : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var btnSubscript: LinearLayout? = null
    private var txtSubscriptPrice: TextView? = null
    private var etVolume: EditText? = null
    private var strVolume: Int? = 1
    private var price: Double? = 1.00
    private var txtStandard: TextView? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_navbar_project_subscription, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        btnSubscript = view?.findViewById(R.id.btn_subscript)
        btnSubscript?.setOnClickListener{
            val subscriptionConfirmDialog: SubscriptionConfirmDialog = SubscriptionConfirmDialog.newInstance()
            subscriptionConfirmDialog.show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
        }

        txtStandard = view?.findViewById(R.id.txt_standard)
        txtSubscriptPrice = view?.findViewById(R.id.txt_subscript_price)
        etVolume = view?.findViewById(R.id.et_input_volume)

        txtStandard?.text = arguments?.getString("title")

        etVolume?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                strVolume = if(!etVolume?.text.toString().isNullOrEmpty()){
                    etVolume?.text.toString().toInt()
                }else{
                    0
                }
                price = (3.90 * strVolume!!)
                txtSubscriptPrice?.text = price.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        return view
    }

    //companion object == static
    companion object {
        fun newInstance(
            title: String?,
        ): SubscriptionBottomSheet {
            val subscriptionBottomSheetDialog = SubscriptionBottomSheet()
            val args = Bundle()
            args.putString("title",title)
            subscriptionBottomSheetDialog.arguments = args
            return subscriptionBottomSheetDialog
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
