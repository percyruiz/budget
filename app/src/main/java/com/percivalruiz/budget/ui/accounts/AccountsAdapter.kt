package com.percivalruiz.budget.ui.accounts

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.percivalruiz.budget.R
import com.percivalruiz.budget.data.Account
import java.text.NumberFormat
import java.util.*

class AccountsAdapter(
    private val values: List<Account>
) : RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_account_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.id.text = (position+1).toString()
        holder.name.text = item.name

        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("PHP")

        holder.balance.text = format.format(item.balance)
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