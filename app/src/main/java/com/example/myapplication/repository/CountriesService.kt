package com.example.myapplication.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface CountriesService {
    @GET("/v3.1/alpha?codes=pl,de,cz,es,pt,fr,se,it,nl,fi,gr,br,gb")
    suspend fun getCountriesResponse(): Response<List<Country>>

    @GET("/v3.1/alpha/{ccn3}")
    suspend fun getCountryDetailsResponse(@Path("ccn3") ccn3: String): Response<List<Country>>

    companion object {
        private const val API_URL = "https://restcountries.com/"

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val countriesService: CountriesService by lazy {
            retrofit.create(CountriesService::class.java)
        }
    }
}