package com.example.newsapp.ui.newsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.newsapp.domain.model.NewsResponse
import com.example.newsapp.ui.newsapp.domain.repository.Repository
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<NewsResponse> = MutableLiveData()


    fun getNews(news: String) {
        viewModelScope.launch {
            val response = repository.getNews(news)
            myResponse.value = response
        }
    }
}