package com.example.apitest2.network.models.person

import com.example.apitest2.network.models.Response

class NamesSearchResponse(val searchType: String,
                          val expression: String,
                          val results: List<PersonDto>) : Response()  {
}