package com.example.randomperson.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomperson.model.Person
import com.example.randomperson.service.RandomPersonService
import kotlinx.coroutines.launch

class PersonDetailViewModel(
    private val personService: RandomPersonService
) : ViewModel() {

    val person = MutableLiveData<Person>()

    val editable = MutableLiveData<Boolean>(false)

    val showNotFound = MutableLiveData<Boolean>(false)

    val isNew = MutableLiveData<Boolean>(false)

    val closeActivity = MutableLiveData<Boolean>(false)

    fun openWithNewPerson(){
        isNew.value = true

        viewModelScope.launch {
            try {
                person.value = personService.getNewRandomPerson()
            }
            catch (e: Exception) {
                Log.v("RandomPersonApp", "Api call unsuccessful: " + e.message)
            }
        }

    }

    fun openPerson(id: Int) {
        isNew.value = false

        viewModelScope.launch {
            try {
                person.value = personService.getSavedPerson(id)
            }
            catch (e: Exception) {
                Log.v("RandomPersonApp", "Person load unsuccessful: " + e.message)
            }
        }
    }

    fun savePerson() {
        viewModelScope.launch {
            try {
                if (person.value == null)
                    return@launch

                personService.savePerson(person.value!!)
                closeActivity.value = true
            } catch (e: Exception) {
                Log.v("RandomPersonApp", "Save person unsuccessful: " + e.message)
            }
        }
    }

    fun deletePerson() {
        viewModelScope.launch {
            try {
                if (person.value == null)
                    return@launch

                if (person.value!!.id == null)
                    return@launch

                personService.deletePerson(person.value!!)
                closeActivity.value = true
            } catch (e: Exception) {
                Log.v("RandomPersonApp", "Delete person unsuccessful: " + e.message)
            }
        }
    }
}