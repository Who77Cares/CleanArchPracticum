package com.example.apitest2.util

import RouterImpl
import com.example.apitest2.navigation.navigation_fragment.Router
import org.koin.dsl.module


val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}