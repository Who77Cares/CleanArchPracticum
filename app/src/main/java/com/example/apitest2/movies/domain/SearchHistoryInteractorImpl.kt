package com.example.apitest2.movies.domain

import com.example.apitest2.movies.domain.models.Movie

class SearchHistoryInteractorImpl(
    private val repository: SearchHistoryRepository
) : SearchHistoryInteractor {

    override fun getHistory(consumer: SearchHistoryInteractor.HistoryConsumer) {
        consumer.consume(repository.getHistory().data)
    }

    override fun saveToHistory(m: Movie) {
        repository.saveToHistory(m)
    }

}