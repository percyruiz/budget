package com.percivalruiz.budget.ui.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.percivalruiz.budget.R
import com.percivalruiz.budget.data.Account
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountsFragment : Fragment() {

    private var columnCount = 1
    private val viewModel by viewModel<AccountsViewModel>()
    private val accounts = mutableListOf<Account>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accountsRecyclerView = view.findViewById<RecyclerView>(R.id.accounts_list)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>("IS_UPDATED")
            ?.observe(
                viewLifecycleOwner
            ) { reload ->
                if(reload) {
                    viewModel.getAccounts()
                }
            }

        val accountsAdapter = AccountsAdapter(accounts) { uid ->
            findNavController().navigate(
                AccountsFragmentDirections.actionAccountsToUpdateAccount(uid)
            )
        }

        with(accountsRecyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = accountsAdapter
        }

        viewModel.accounts.observe(viewLifecycleOwner) {
            accounts.clear()
            accounts.addAll(it)
            accountsRecyclerView.smoothScrollToPosition(accounts.size)
            accountsAdapter.notifyDataSetChanged()
        }

        fab.setOnClickListener {
            findNavController().navigate(AccountsFragmentDirections.actionAccountsToAddAccount())
            viewModel.getAccounts()
        }
    }
}