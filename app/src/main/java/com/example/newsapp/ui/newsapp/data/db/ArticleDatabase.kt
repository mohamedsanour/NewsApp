package com.example.newsapp.ui.newsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.ui.newsapp.domain.model.Article
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao


    companion object{
        @Volatile
        private var INSATNCE : ArticleDatabase? = null
        fun getDatabase(context: Context) : ArticleDatabase{
            val tempInstance = INSATNCE
            if (tempInstance != null){
                return tempInstance
            }
            kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSATNCE = instance
                return instance
            }
        }
    }
}