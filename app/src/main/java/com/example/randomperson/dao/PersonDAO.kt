package com.example.randomperson.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.randomperson.entity.PersonData
import com.example.randomperson.model.PersonListModel

@Dao
interface PersonDAO {
    @Insert
    suspend fun insertPerson(person: PersonData)

    @Update
    suspend fun updatePerson(person: PersonData)

    @Query("SELECT id, name, age FROM person_data")
    suspend fun getPersonList() : List<PersonListModel>

    @Query("SELECT * FROM person_data WHERE id = :id")
    suspend fun getPerson(id: Int) : PersonData?

    @Delete
    suspend fun deletePerson(person: PersonData)
}