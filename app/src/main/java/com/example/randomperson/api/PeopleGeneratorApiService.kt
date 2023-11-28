package com.example.randomperson.api

import com.example.randomperson.model.Person
import retrofit2.Response
import retrofit2.http.GET

interface PeopleGeneratorApiService {
    companion object {
        const val PERSON = "api/person"
    }

    @GET(PERSON)
    suspend fun getPerson() : Response<Person>
}