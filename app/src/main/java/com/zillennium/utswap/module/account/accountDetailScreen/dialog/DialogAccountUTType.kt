package com.zillennium.utswap.module.account.accountDetailScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.DialogAccountDetailPopupUtTypeBinding


class DialogAccountUTType : DialogFragment() {

    private var binding: DialogAccountDetailPopupUtTypeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_account_detail_popup_ut_type, container, true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            binding?.apply {

            }
        }catch (error: Exception) {
            // Must be safe
        }

    }

    companion object {
        fun newInstance(): DialogAccountUTType {
            val dialogAccountUTType = DialogAccountUTType()
            val args = Bundle()
            dialogAccountUTType.arguments = args
            return dialogAccountUTType
        }
    }
}
