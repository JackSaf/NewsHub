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
    fun getAllArticles(): Flow<ArticleDbDto>
    @Insert
    suspend fun insertArticle(article: ArticleDbDto)
    @Delete
    suspend fun deleteArticle(vararg articles: ArticleDbDto)
}