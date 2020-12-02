package com.percivalruiz.budget.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.percivalruiz.budget.data.dao.AccountDao

@Database(entities = [Account::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}