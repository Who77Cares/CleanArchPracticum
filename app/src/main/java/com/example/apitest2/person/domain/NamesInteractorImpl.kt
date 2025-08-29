package com.example.apitest2.person.domain

import com.example.apitest2.person.domain.api.NamesRepository
import com.example.apitest2.util.Resource
import java.util.concurrent.Executors

class NamesInteractorImpl (private val repository: NamesRepository) : NamesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute {
            when(val resource = repository.searchNames(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }
}