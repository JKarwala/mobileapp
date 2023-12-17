package com.example.myapplication

import com.example.myapplication.repository.Country

class UIState<T> (
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)