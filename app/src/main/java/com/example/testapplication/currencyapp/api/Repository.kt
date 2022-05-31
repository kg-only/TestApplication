package com.example.testapplication.currencyapp.api

import android.util.Log
import com.example.testapplication.currencyapp.db.repo.RemoteRepository
import com.example.testapplication.currencyapp.models.Rates
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository(private val api: Api) : RemoteRepository {
    override suspend fun loadFromRemoteRepos(): List<Rates> {
        return listOfNotNull(api.getCurrency().rates)

    }

    override suspend fun test(map: Map<String, String>) {
        api.search(map).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {

                    Log.e("####",response.raw().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })
    }
}