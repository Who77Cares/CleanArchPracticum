package com.example.apitest2.person.domain

interface NamesInteractor {
    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
}