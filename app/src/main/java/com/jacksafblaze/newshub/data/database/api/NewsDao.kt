package com.jacksafblaze.newshub.data.database.api

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jacksafblaze.newshub.data.database.model.ArticleDbDto
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("Select * from articles")
    fun getAllArticles(): Flow<List<ArticleDbDto>>
    @Insert
    suspend fun insertArticle(article: ArticleDbDto)
    @Delete
    suspend fun deleteArticles(vararg articles: ArticleDbDto)
}