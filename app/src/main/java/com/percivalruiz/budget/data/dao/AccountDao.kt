package com.percivalruiz.budget.data.dao

import androidx.room.*
import com.percivalruiz.budget.data.Account

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    fun getAll(): List<Account>


    @Query("SELECT * FROM account where uid = :uid")
    fun getAccount(uid: Int): Account

    @Insert
    fun insertAll(vararg accounts: Account)

    @Update
    fun update(account: Account)

    @Delete
    fun delete(account: Account)
}