package com.example.abcnavcomponent.abcnavcomp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    //    https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=42e1fb65aa9d7209eb6dfc56760ea031
    @GET("search")
    fun search(
        @Query("numbers") numbers: List<String>
    ): Call<ResponseBody>

}