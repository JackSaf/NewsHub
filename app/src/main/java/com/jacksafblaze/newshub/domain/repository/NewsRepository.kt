package com.jacksafblaze.newshub.domain.repository

import androidx.paging.PagingData
import com.jacksafblaze.newshub.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getSearchedNews(query: String): Flow<PagingData<Article>>
    fun getTopHeadlines(): Flow<PagingData<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticles(vararg articles: Article)
    fun viewSavedArticles(): Flow<List<Article>>
}