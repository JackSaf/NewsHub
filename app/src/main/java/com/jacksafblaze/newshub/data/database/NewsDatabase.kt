package com.jacksafblaze.newshub.data.database

import androidx.room.Database
import com.jacksafblaze.newshub.data.database.api.NewsDao
import com.jacksafblaze.newshub.data.database.model.ArticleDbDto
import com.jacksafblaze.newshub.data.database.model.SourceDbDto

@Database(entities = [ArticleDbDto::class], version = 1)
abstract class NewsDatabase {
    abstract fun newsDao(): NewsDao
}