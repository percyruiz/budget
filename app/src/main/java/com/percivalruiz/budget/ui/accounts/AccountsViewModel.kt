package com.percivalruiz.budget.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.percivalruiz.budget.data.Account
import com.percivalruiz.budget.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountsViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    init {
        getAccounts()
    }

    fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            _accounts.postValue(accountRepository.getAccounts())
        }
    }
}
