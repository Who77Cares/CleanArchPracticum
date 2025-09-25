package com.example.apitest2.util

import com.example.apitest2.cast.data.CastConverter
import com.example.apitest2.network.MoviesRepositoryImpl
import com.example.apitest2.movies.data.storage.SearchHistoryRepositoryImpl
import com.example.apitest2.network.RetrofitNetworkClient
import com.example.apitest2.movies.data.storage.PrefsStorageClient
import com.example.apitest2.network.api.MoviesInteractor
import com.example.apitest2.network.api.MoviesRepository
import com.example.apitest2.movies.domain.SearchHistoryInteractor
import com.example.apitest2.movies.domain.SearchHistoryRepository
import com.example.apitest2.network.domain.MoviesInteractorImpl
import com.example.apitest2.movies.domain.SearchHistoryInteractorImpl
import com.example.apitest2.movies.domain.models.Movie
import com.example.apitest2.network.NamesRepositoryImpl
import com.example.apitest2.network.api.NetworkClient
import com.example.apitest2.person.domain.NamesInteractor
import com.example.apitest2.person.domain.NamesInteractorImpl
import com.example.apitest2.person.domain.api.NamesRepository
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    single<NetworkClient> { RetrofitNetworkClient(androidContext()) }


    // MoviesRepository
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
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


    factory<CastConverter> {
        CastConverter()
    }




    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }

    single<NamesInteractor> {
        NamesInteractorImpl(get())
    }

}