package com.example.apitest2.movies.data.storage

import com.example.apitest2.movies.domain.SearchHistoryRepository
import com.example.apitest2.movies.domain.models.Movie
import com.example.apitest2.util.Resource

class SearchHistoryRepositoryImpl(
    private val storage: StorageClient<ArrayList<Movie>>
): SearchHistoryRepository
 {
     override fun saveToHistory(m: Movie) {
         val movies = storage.getData() ?: arrayListOf()
         movies.add(m)
         storage.storageData(movies)
     }

     override fun getHistory(): Resource<List<Movie>> {
         val movies = storage.getData() ?: listOf()
         return Resource.Success(movies)
     }
 }