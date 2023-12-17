package com.example.myapplication.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CountriesService {
    @GET("/v3.1/alpha?codes=pl,de,cz,es,pt,fr,se,it,nl,fi,gr,br,gb")
    suspend fun getCountriesResponse(): Response<List<Country>>

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