package com.crp.shaadimilan

import android.app.Application
import com.crp.shaadimilan.di.networkModule
import com.crp.shaadimilan.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShaadiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ShaadiApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}