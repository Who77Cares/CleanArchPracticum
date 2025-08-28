package com.example.apitest2.navigation

import android.app.Application
import com.example.apitest2.util.domainModule
import com.example.apitest2.util.navigationModule
import com.example.apitest2.util.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MoviesApplication)
            modules(listOf(domainModule, viewModelModule, navigationModule))
        }

    }

}
// Теперь у нас есть возможность вставить объекты Router и NavigatorHolder в наши фрагменты и Activity.