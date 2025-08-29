package com.example.apitest2.person.domain.api

import com.example.apitest2.person.domain.Person
import com.example.apitest2.util.Resource

interface NamesRepository {
    fun searchNames(expression: String): Resource<List<Person>>
}