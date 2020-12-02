package com.percivalruiz.budget.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.percivalruiz.budget.R
import com.percivalruiz.budget.utils.hideKeyboard
import com.percivalruiz.budget.utils.safeToDouble
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UpdateAccountFragment : Fragment() {

    private val args: UpdateAccountFragmentArgs by navArgs()
    private val viewModel by viewModel<UpdateAccountViewModel> {
        parametersOf(args.uid)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountName = view.findViewById<TextView>(R.id.account_name)
        val accountBalance = view.findViewById<TextView>(R.id.account_balance)
        val saveButton = view.findViewById<Button>(R.id.save)
        val deleteButton = view.findViewById<Button>(R.id.delete)

        viewModel.account.observe(viewLifecycleOwner) {
            it.apply {
                accountName.text = name
                accountBalance.text = balance.toString()
            }
        }

        saveButton.setOnClickListener {

            viewModel.updateAccount(
                    uid = args.uid,
                    name = accountName.text.toString(),
                    balance = accountBalance.text.toString().safeToDouble()

            )

            hideKeyboard()
            setUpdated(true)
            viewModel.getAccount(args.uid)
        }

        deleteButton.setOnClickListener {
            viewModel.deleteAccount()
            setUpdated(true)
            requireActivity().onBackPressed()
        }

    }

    private fun setUpdated(isUpdated: Boolean) {

        findNavController().previousBackStackEntry?.savedStateHandle?.set("IS_UPDATED", isUpdated)
    }
}