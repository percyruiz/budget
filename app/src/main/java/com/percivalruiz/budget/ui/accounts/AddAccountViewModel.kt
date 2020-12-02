package com.percivalruiz.budget.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.percivalruiz.budget.data.Account
import com.percivalruiz.budget.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddAccountViewModel(
    private val accountRepository: AccountRepository
): ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account> = _account

    fun addAccount(name: String, balance: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.insert(name, balance)
        }
    }
}