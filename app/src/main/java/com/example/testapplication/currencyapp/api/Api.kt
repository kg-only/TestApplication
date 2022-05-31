package com.example.testapplication.currencyapp.api


import com.example.testapplication.currencyapp.models.Currency
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {
    @GET("latest?access_key=1fff7bf2a481d28ef8a1c1f5ac54920e&format=1")
    suspend fun getCurrency(): Currency
    @GET("search")
    suspend fun search(@QueryMap numbers: Map<String, String>) : Call<ResponseBody>

}