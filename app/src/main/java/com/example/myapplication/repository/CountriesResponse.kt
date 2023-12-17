package com.example.myapplication.repository

data class Country (
    val name: Name,
    val population: Int,
    val flags: Flags
)

data class Name (
    val common: String
)

data class Flags (
    val png: String
)

