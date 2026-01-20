package com.example.newsapp.ui.newsapp.data.db

import androidx.room.TypeConverter
import com.example.newsapp.ui.newsapp.domain.model.Source

class Convertors {
    @TypeConverter
    fun fromSource(source: Source?): String?{
        return source?.name
    }
    @TypeConverter
    fun toSource(name: String?) : Source?{
        return if (name != null) Source(null, name) else null
    }
}