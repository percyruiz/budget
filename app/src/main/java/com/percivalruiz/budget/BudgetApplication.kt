package com.percivalruiz.budget

import android.app.Application
import com.percivalruiz.budget.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BudgetApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@BudgetApplication)
            // declare modules
            modules(appModule)
        }
    }
}