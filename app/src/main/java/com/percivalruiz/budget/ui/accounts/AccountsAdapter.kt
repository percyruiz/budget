package com.percivalruiz.budget.ui.accounts

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.percivalruiz.budget.R
import com.percivalruiz.budget.data.Account
import com.percivalruiz.budget.utils.formatMoney
import java.text.NumberFormat
import java.util.*

class AccountsAdapter(
    private val values: List<Account>,
    private val click: (id: Int) -> Unit
) : RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_account_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.id.text = item.uid.toString()
        holder.name.text = item.name

        holder.balance.text = item.balance.formatMoney()
        holder.itemView.setOnClickListener {
            click(item.uid)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.item_number)
        val name: TextView = view.findViewById(R.id.name)
        val balance: TextView = view.findViewById(R.id.balance)

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }
}