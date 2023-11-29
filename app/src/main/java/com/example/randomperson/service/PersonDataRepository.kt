package com.example.randomperson.service

import androidx.lifecycle.LiveData
import com.example.randomperson.dao.PersonDAO
import com.example.randomperson.entity.PersonData
import com.example.randomperson.model.Person
import com.example.randomperson.model.PersonListModel

class PersonDataRepository(private val personDAO: PersonDAO) {

    suspend fun insertPerson(person: Person?){
        if (person == null)
            return;

        personDAO.insertPerson(PersonData.create(person))
    }

    suspend fun getPerson(id: Int) : Person? {
        val res = personDAO.getPerson(id) ?: return null;
        return Person.create(res)
    }

    suspend fun getPersonList(): List<PersonListModel> {
        return personDAO.getPersonList()
    }

    suspend fun deletePerson(person: Person) {
        val personData = PersonData.create(person)
        personDAO.deletePerson(personData)
    }
}