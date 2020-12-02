package com.percivalruiz.budget.di

import androidx.room.Room
import com.percivalruiz.budget.data.AppDatabase
import com.percivalruiz.budget.data.dao.AccountDao
import com.percivalruiz.budget.data.repository.AccountRepository
import com.percivalruiz.budget.ui.accounts.AccountsViewModel
import com.percivalruiz.budget.ui.accounts.AddAccountViewModel
import com.percivalruiz.budget.ui.accounts.UpdateAccountViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "budget"
        ).build()
    }

    single<AccountDao> {
        val db = get<AppDatabase>()
        db.accountDao()
    }

    single<AccountRepository> {
        AccountRepository(accountDao = get())
    }

    viewModel {
        AccountsViewModel(
            accountRepository = get()
        )
    }

    viewModel { (uid: Int) ->
        UpdateAccountViewModel(
            accountRepository = get(),
            uid = uid
        )
    }

    viewModel {
        AddAccountViewModel(
            accountRepository = get()
        )
    }
}