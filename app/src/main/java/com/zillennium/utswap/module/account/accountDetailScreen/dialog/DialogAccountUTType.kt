package com.zillennium.utswap.module.account.accountDetailScreen.dialog

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.databinding.DialogAccountDetailPopupUtTypeBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.account.accountDetailScreen.adapter.AccountUTTypeCriteriaAdapter
import com.zillennium.utswap.module.account.accountDetailScreen.adapter.AccountUTTypePriorityAdapter
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import rx.Subscription


class DialogAccountUTType : DialogFragment() {

    private var binding: DialogAccountDetailPopupUtTypeBinding? = null

    private var subscriptions: Subscription? = null
    private var accountUTTypeCriteriaAdapter: AccountUTTypeCriteriaAdapter? = null
    private var accountUtTypePriorityAdapter: AccountUTTypePriorityAdapter? = null

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
                textAccountUtType.text = arguments?.getString("title").toString()
                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvCriteria.layoutManager = linearLayoutManager

                val linearLayoutManagerPriority = LinearLayoutManager(requireContext())
                rvPriority.layoutManager = linearLayoutManagerPriority

                progressBar.visibility = View.VISIBLE

                onCallAccountTypeDetail(requireContext())
            }
        }catch (error: Exception) {
            // Must be safe
        }

    }

    private fun onCallAccountTypeDetail(context: Context){
        subscriptions?.unsubscribe()
        subscriptions = ApiUserImp().appSideBarUserInfo(context).subscribe({
            if(it.status == 1)
            {
                it.data?.doc_user_lavel?.let { it1 -> onGetDataSuccess(it1) }
            }else{
                if(it.message.toString() == "Please sign in"){
                    ClientClearData.clearDataUser()
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    dismiss()
                    requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }else{
                    Toast.makeText(UTSwapApp.instance,it.message.toString(),Toast.LENGTH_LONG).show()
                    dismiss()
                }
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {

                }
            }
        })
    }

    private fun onGetDataSuccess(data: User.AppSideBarDocUserLevel){
        binding?.apply {

            tvTilePriority.visibility = View.VISIBLE
            tvTitleCriteria.visibility = View.VISIBLE
            lineCriteria.visibility = View.VISIBLE
            linePriority.visibility = View.VISIBLE
            textAccountUtType.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            if(data.criteria.isNullOrEmpty()){
                linearNotAvailableCriteria.visibility = View.VISIBLE
            }else{
                accountUTTypeCriteriaAdapter = AccountUTTypeCriteriaAdapter()
                accountUTTypeCriteriaAdapter?.items = data.criteria as ArrayList<User.CriteriaList>
                rvCriteria.adapter = accountUTTypeCriteriaAdapter
            }

            if(data.priority_and_privileges.isNullOrEmpty()){
                linearNotAvailablePriority.visibility = View.VISIBLE
            }else{
                accountUtTypePriorityAdapter = AccountUTTypePriorityAdapter()
                accountUtTypePriorityAdapter?.items = data.priority_and_privileges as ArrayList<User.PriorityAndPrivilegesList>
                rvPriority.adapter = accountUtTypePriorityAdapter
            }
        }
    }

    companion object {
        fun newInstance(
            title: String?,
        ): DialogAccountUTType {
            val dialogAccountUTType = DialogAccountUTType()
            val args = Bundle()
            args.putString("title",title)
            dialogAccountUTType.arguments = args
            return dialogAccountUTType
        }
    }
}
