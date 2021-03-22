package com.vvebdevelopment.weatherapp.data

import androidx.lifecycle.LiveData

class CityRepository(private val cityDao: CityDao) {

    val readAllData: LiveData<List<City>> = cityDao.readAllData()

    suspend fun addCity(city: City) {
        cityDao.addCity(city)
    }
}