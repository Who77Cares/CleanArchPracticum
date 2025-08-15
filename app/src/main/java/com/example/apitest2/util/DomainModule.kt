package com.example.apitest2.util

import com.example.apitest2.data.MoviesRepositoryImpl
import com.example.apitest2.data.SearchHistoryRepositoryImpl
import com.example.apitest2.data.network.RetrofitNetworkClient
import com.example.apitest2.data.storage.PrefsStorageClient
import com.example.apitest2.domain.api.MoviesInteractor
import com.example.apitest2.domain.api.MoviesRepository
import com.example.apitest2.domain.api.SearchHistoryInteractor
import com.example.apitest2.domain.api.SearchHistoryRepository
import com.example.apitest2.domain.impl.MoviesInteractorImpl
import com.example.apitest2.domain.impl.SearchHistoryInteractorImpl
import com.example.apitest2.domain.models.Movie
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    // MoviesRepository
    single<MoviesRepository> {
        MoviesRepositoryImpl(RetrofitNetworkClient(androidContext()))
    }

    // MoviesInteractor
    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    // SearchHistoryRepository
    single<SearchHistoryRepository> {
        SearchHistoryRepositoryImpl(
            PrefsStorageClient<ArrayList<Movie>>(
                androidContext(),
                "HISTORY",
                object : TypeToken<ArrayList<Movie>>() {}.type
            )
        )
    }

    // SearchHistoryInteractor
    single<SearchHistoryInteractor> {
        SearchHistoryInteractorImpl(get())
    }
}