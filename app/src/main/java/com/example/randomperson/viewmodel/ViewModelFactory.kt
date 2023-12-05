package com.example.randomperson.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomperson.service.RandomPersonService

class ViewModelFactory(
    private val personService: RandomPersonService
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonDetailViewModel::class.java)) {
            return PersonDetailViewModel(personService) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(personService) as T
        }

        throw IllegalArgumentException("Wrong ViewModel class")
    }
}