package com.example.randomperson.model

import com.example.randomperson.entity.PersonData
import com.google.gson.annotations.Expose

data class Person(
    @Expose(deserialize = false, serialize = false)
    val id: Int,

    val name: String,
    val age: String,
    val job: String,
    val email: String,
    val gender: String,
    val username: String,
    val address: Address
) {
    companion object Factory {
        fun create(personData: PersonData) : Person {
            return Person(
                personData.id,
                personData.name,
                personData.age,
                personData.job,
                personData.email,
                personData.gender,
                personData.username,
                Address(
                    personData.street,
                    personData.city,
                    personData.state,
                    personData.country,
                    personData.zip
                )
            )
        }
    }
}
