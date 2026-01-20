package com.example.newsapp.ui.newsapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.newsapp.data.db.ArticleDatabase
import com.example.newsapp.ui.newsapp.domain.model.Article
import com.example.newsapp.ui.newsapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Article>>
    private val repository: UserRepository

    init {
        val userDao = ArticleDatabase.getDatabase(application).getArticleDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData

    }

    fun addArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(article)
        }
    }

    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(article)
        }
    }
    fun isArticleSaved(article: Article, callback: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val saved = repository.isSaved(article.url ?: "")
            callback(saved)
        }
    }


}