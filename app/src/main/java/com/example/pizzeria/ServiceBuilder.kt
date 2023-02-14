package com.example.mydailydiet.model

import com.example.pizzeria.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    lateinit var service: APIService
    val URL = "http://10.0.2.2:8080/"

    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(APIService::class.java)
    }
}