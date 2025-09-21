package com.example.apitest2.person.domain

import kotlinx.coroutines.flow.Flow

interface NamesInteractor {

    // Объекты, с которыми работает этот Flow, — это экземпляры класса Pair (пара), первый элемент которой — список найденных людей, а второй — сообщение об ошибке.
    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>
}