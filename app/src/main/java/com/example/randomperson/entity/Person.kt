package com.example.randomperson.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.joinIntoString
import com.example.randomperson.model.Person

@Entity(tableName = "person_data")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "age")
    val age: String,

    @ColumnInfo(name = "job")
    val job: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "address_street")
    val street: String,

    @ColumnInfo(name = "address_city")
    val city: String,

    @ColumnInfo(name = "address_city")
    val state: String,

    @ColumnInfo(name = "address_country")
    val country: String,

    @ColumnInfo(name = "address_zip")
    val zip: String
) {
    companion object Factory {
        fun create(person: Person) : PersonData {
            return PersonData(
                person.id,
                person.name,
                person.age,
                person.job,
                person.email,
                person.gender,
                person.username,
                person.address.streetAddress,
                person.address.city,
                person.address.state,
                person.address.country,
                person.address.zip
            )
        }
    }
}
