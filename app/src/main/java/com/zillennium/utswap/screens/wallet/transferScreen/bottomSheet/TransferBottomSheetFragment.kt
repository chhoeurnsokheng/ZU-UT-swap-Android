package com.zillennium.utswap.screens.wallet.transferScreen.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R


class TransferBottomSheetFragment : BottomSheetDialogFragment() {
    private var btnTransfer: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_transfer_bottomsheet_fragment, container, false)
        btnTransfer = view.findViewById(R.id.btn_transfer_confirm)
        btnTransfer?.setOnClickListener(View.OnClickListener { dismiss() })
        return view
    }

    companion object {
        fun newInstance(): TransferBottomSheetFragment {
            val transferBottomSheetFragment = TransferBottomSheetFragment()
            transferBottomSheetFragment.arguments = Bundle()
            return transferBottomSheetFragment
        }
    }
}