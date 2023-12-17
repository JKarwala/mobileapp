package com.example.myapplication.repository

import retrofit2.Response

class CountriesRepository {
    suspend fun getCountriesResponse(): Response<List<Country>> =
        CountriesService.countriesService.getCountriesResponse()
}