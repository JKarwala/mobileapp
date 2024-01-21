package com.example.myapplication.repository

import retrofit2.Response

class CountriesRepository {
    suspend fun getCountriesResponse(): Response<List<Country>> =
        CountriesService.countriesService.getCountriesResponse()

    suspend fun getCountriesDetailsResponse(ccn3: String): Response<List<Country>> =
        CountriesService.countriesService.getCountryDetailsResponse(ccn3)
}