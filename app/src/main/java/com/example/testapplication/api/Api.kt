package com.example.testapplication.api

import com.example.testapplication.models.Currency
import retrofit2.http.GET

interface Api {
    @GET("latest?access_key=1fff7bf2a481d28ef8a1c1f5ac54920e&format=1")
    suspend fun getCurrency(): Currency
}