package com.example.randomperson

import android.app.Application
import com.example.randomperson.api.PeopleGeneratorApiService
import com.example.randomperson.service.PersonDataRepository
import com.example.randomperson.service.RandomPersonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomPersonApplication : Application() {
    val apiService: PeopleGeneratorApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://peoplegeneratorapi.live/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PeopleGeneratorApiService::class.java)
    }

    val personService: RandomPersonService by lazy {
        RandomPersonService(
            apiService,
            PersonDataRepository(getDatabase(this).personDAO()))
    }
    override fun onCreate() {
        super.onCreate()
    }
}