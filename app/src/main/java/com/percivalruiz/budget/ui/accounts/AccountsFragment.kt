package com.percivalruiz.budget.ui.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.percivalruiz.budget.R
import com.percivalruiz.budget.data.Account

class AccountsFragment : Fragment() {

    private var columnCount = 1
    private var accounts =
        mutableListOf(
            Account("Savings", 123213.00),
            Account("Payroll", 12331.00),
            Account("Checking", 12414.00)
        )

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

        val accountsAdapter = AccountsAdapter(accounts)
        with(accountsRecyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = accountsAdapter
        }

        fab.setOnClickListener {
            accounts.add(
                Account("Spending", 123213.00)
            )
            accountsRecyclerView.smoothScrollToPosition(accounts.size)
            accountsAdapter.notifyDataSetChanged()
        }
    }
}