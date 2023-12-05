package com.example.randomperson.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomperson.model.PersonListModel
import com.example.randomperson.service.RandomPersonService
import kotlinx.coroutines.launch

class MainViewModel(
    private val personService: RandomPersonService
) : ViewModel() {

    val list = MutableLiveData<List<PersonListModel>>(listOf())

    fun loadData(){
        viewModelScope.launch {
            try {
                list.value = personService.getSavedPersonList()
                var t = ""
            } catch (e: Exception) {
                Log.v("RandomPersonApp", "Person list load unsuccessful: " + e.message)
            }
        }
    }

}