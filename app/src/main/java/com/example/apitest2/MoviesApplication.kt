package com.example.apitest2

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.apitest2.util.domainModule
import com.example.apitest2.util.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MoviesApplication)
            modules(listOf(domainModule, viewModelModule))
        }

    }

}