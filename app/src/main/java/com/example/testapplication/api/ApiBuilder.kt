package com.example.testapplication.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "http://api.exchangeratesapi.io/v1/"
//http://api.exchangeratesapi.io/v1/latest?access_key=1fff7bf2a481d28ef8a1c1f5ac54920e&format=1

object ApiBuilder {
    fun create() = module {
        single {
            ///OkHTTP
            val interceptor = HttpLoggingInterceptor()//read about this
            interceptor.level = HttpLoggingInterceptor.Level.BODY//read about this
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            //retrofit
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            retrofit.create(Api::class.java)
        }

    }
}