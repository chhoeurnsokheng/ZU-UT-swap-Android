package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetNavbarProjectSubscriptionBinding

import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.dialog.SubscriptionConfirmDialog
import com.zillennium.utswap.utils.groupingSeparator


class SubscriptionBottomSheet : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetNavbarProjectSubscriptionBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_navbar_project_subscription, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            btnSubscript.setOnClickListener{
                if(!etInputVolume.text.isNullOrEmpty()){
                    if(etInputVolume.text.toString().toLong() > 0){
                        dismiss()
                        val subscriptionConfirmDialog: SubscriptionConfirmDialog = SubscriptionConfirmDialog.newInstance()
                        subscriptionConfirmDialog.show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
                    }
                }

            }

            txtStandard.text = arguments?.getString("title")

            etInputVolume.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }


                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val strVolume = if(!etInputVolume.text.toString().isNullOrEmpty()){
                        etInputVolume.text.toString().toLong()
                    }else{
                        0
                    }
                    val price = (3.90 * strVolume)
                    txtSubscriptPrice.text = groupingSeparator(price)
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }
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
