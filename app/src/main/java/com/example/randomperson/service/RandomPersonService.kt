package com.example.randomperson.service

import com.example.randomperson.api.PeopleGeneratorApiService
import com.example.randomperson.model.Person
import com.example.randomperson.model.PersonListModel

class RandomPersonService(
    private val apiService: PeopleGeneratorApiService,
    private val personRepository: PersonDataRepository
) {

    suspend fun getNewRandomPerson() : Person? {
        val response = apiService.getPerson()

        if (response.isSuccessful)
            return response.body()
        return null
    }


    suspend fun getSavedPerson(id: Int) : Person? {
        return personRepository.getPerson(id)
    }

    suspend fun getSavedPersonList() : List<PersonListModel> {
        return personRepository.getPersonList()
    }

    suspend fun savePerson(person: Person) {
        if (person.id == null || person.id == 0)
            personRepository.insertPerson(person)
        else
            personRepository.updatePerson(person)
    }

    suspend fun deletePerson(person: Person) {
        personRepository.deletePerson(person)
    }
}