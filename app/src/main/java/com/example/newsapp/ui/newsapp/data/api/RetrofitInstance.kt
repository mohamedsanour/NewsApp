package com.example.newsapp.ui.newsapp.data.api

import com.example.newsapp.ui.newsapp.util.const.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : Service by lazy {
        retrofit.create(Service::class.java)
    }
}