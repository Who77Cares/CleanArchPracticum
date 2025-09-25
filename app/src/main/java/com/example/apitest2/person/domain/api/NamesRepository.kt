package com.example.apitest2.person.domain.api

import com.example.apitest2.person.domain.Person
import com.example.apitest2.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {
    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}