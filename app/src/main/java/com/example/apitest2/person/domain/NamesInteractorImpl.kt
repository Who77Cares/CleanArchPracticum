package com.example.apitest2.person.domain

import com.example.apitest2.person.domain.api.NamesRepository
import com.example.apitest2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class NamesInteractorImpl (private val repository: NamesRepository) : NamesInteractor {




    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }

        }
    }
}

// Имейте в виду, что в операторе map, независимо от типа данных
// входящего потока, дальше можно отправлять объекты совершенно другого типа,
// если того требует логика. Главное, чтобы на выходе тип данных потока
// соответствовал объявленному (в нашем случае Flow<Pair<List<Person>?, String?>>).