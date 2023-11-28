package com.example.randomperson

import com.example.randomperson.model.Person
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ApiTest {

    @Test
    fun testCallApi() = runBlocking {
        val app = RandomPersonApplication()
        val apiService = app.apiService
        val res = apiService.getPerson()

        assertEquals(res.isSuccessful, true)

        val body =  res.body()

        assertNotEquals(body, null)
        assertThat(body, instanceOf(Person::class.java))
    }
}