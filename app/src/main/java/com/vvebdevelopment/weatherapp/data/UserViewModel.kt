package com.vvebdevelopment.weatherapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application): AndroidViewModel(application){

    val readAllData: LiveData<List<City>>
    val repository: CityRepository

    init{
        val cityDao = CityDatabase.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
        readAllData = repository.readAllData
    }

    fun addCity(city: City){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCity(city)
        }
    }
}