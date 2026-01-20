package com.example.newsapp.ui.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.newsapp.domain.model.NewsResponse
import com.example.newsapp.ui.newsapp.domain.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<NewsResponse> = MutableLiveData()
    val myResponse2: MutableLiveData<NewsResponse> = MutableLiveData()
    val searchResponse: MutableLiveData<NewsResponse> = MutableLiveData()



    fun getBreakingNews() {
        viewModelScope.launch {
            val fetchNewsDeferred = async { repository.getBreakingNews() }
            val delayDeferred = async { delay(1000) }
            fetchNewsDeferred.await()
            delayDeferred.await()
            val result = fetchNewsDeferred.await()
            myResponse2.postValue(result)
        }
    }

    fun searchForNews(searchQuery: String) {
        viewModelScope.launch {
            try {
                val response3 = repository.searchForNews(searchQuery)
                searchResponse.value = response3
            } catch (e: Exception) {
                Log.d("SearchError", "Error: ${e.message}")}
        }
    }
}