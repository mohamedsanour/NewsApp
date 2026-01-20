package com.example.newsapp.ui.newsapp.domain.repository

import com.example.newsapp.ui.newsapp.data.api.RetrofitInstance
import com.example.newsapp.ui.newsapp.domain.model.NewsResponse
import com.example.newsapp.ui.newsapp.util.const.Companion.API_KEY

class Repository {
    suspend fun getNews(news : String): NewsResponse {
        return RetrofitInstance.api.getNews(news)
    }

    suspend fun getBreakingNews(): NewsResponse {
        return RetrofitInstance.api.getBreakingNews()
    }
    suspend fun searchForNews(searchQuery : String) : NewsResponse{
        return RetrofitInstance.api.searchForNews(searchQuery)
    }
}