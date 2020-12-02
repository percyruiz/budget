package com.percivalruiz.budget.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.percivalruiz.budget.data.Account
import com.percivalruiz.budget.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateAccountViewModel(
    private val accountRepository: AccountRepository,
    private val uid: Int
): ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account> = _account

    init {
        getAccount(uid)
    }

    fun getAccount(uid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _account.postValue(accountRepository.getAccount(uid))
        }
    }

    fun updateAccount(uid: Int, name: String, balance: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.update(uid, name, balance)
        }
    }

    fun deleteAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            if(_account.value != null) {
                accountRepository.delete(_account.value!!)
            }
        }
    }
}