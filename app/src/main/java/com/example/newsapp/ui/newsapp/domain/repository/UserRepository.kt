package com.example.newsapp.ui.newsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.ui.newsapp.data.db.ArticleDao
import com.example.newsapp.ui.newsapp.domain.model.Article

class UserRepository(private val userDao: ArticleDao) {
    val readAllData: LiveData<List<Article>> = userDao.getAllArticles()

    suspend fun add(article: Article) {
        userDao.insert(article)
    }

    suspend fun delete(article: Article) {
        userDao.deleteByUrl(article.url)
    }

    suspend fun isSaved(url: String): Boolean {
        return userDao.getArticleByUrl(url) != null
    }
}