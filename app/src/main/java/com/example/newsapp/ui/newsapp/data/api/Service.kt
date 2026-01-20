package com.example.newsapp.ui.newsapp.data.api

import com.example.newsapp.ui.newsapp.domain.model.NewsResponse
import com.example.newsapp.ui.newsapp.util.const.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String ,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,

        @Query("page")
        page: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY

    ): NewsResponse

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country: String = "us",

        @Query("page")
        page: Int = 2,

        @Query("apiKey")
        apiKey: String = API_KEY

    ) : NewsResponse
}