package com.percivalruiz.budget.data.repository

import com.percivalruiz.budget.data.Account
import com.percivalruiz.budget.data.dao.AccountDao

class AccountRepository(
    private val accountDao: AccountDao
) {

    fun getAccounts() = accountDao.getAll()

    fun getAccount(uid: Int) = accountDao.getAccount(uid)

    fun insert(name: String, balance: Double) {
        accountDao.insertAll(Account(uid = 0, name = name, balance = balance))
    }

    fun update(uid: Int, name: String, balance: Double) {
        accountDao.update(
            Account(
                uid = uid,
                name = name,
                balance = balance
            )
        )
    }

    fun delete(account: Account) {
        accountDao.delete(account)
    }
}