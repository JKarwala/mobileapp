package com.example.myapplication.repository

data class Country (
    val ccn3: String,
    val name: Name,
    val population: Int,
    val flags: Flags,
    val region: String,
    val capital: List<String>,
    val area: Int
)

data class Name (
    val common: String,
    val official: String
)

data class Flags (
    val png: String
)

