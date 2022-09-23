package com.jacksafblaze.newshub.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jacksafblaze.newshub.data.database.api.NewsDao
import com.jacksafblaze.newshub.data.database.dbmapper.ArticleDatabaseMapper
import com.jacksafblaze.newshub.data.network.api.RetService
import com.jacksafblaze.newshub.data.network.networkmapper.ArticleNetworkEntityMapper
import com.jacksafblaze.newshub.data.network.paging.NewsPagingSource
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val retService: RetService,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getSearchedNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(retService, query) }
        ).flow.map { pagingData ->
            pagingData.map {
                val article = ArticleNetworkEntityMapper.entityToDomainModel(it)
                if (newsDao.isArticleSaved(article.url)) {
                    article.copy(saved = true)
                } else {
                    article
                }
            }
        }
    }

    override suspend fun saveArticle(article: Article) {
        newsDao.insertArticle(ArticleDatabaseMapper.domainModelToEntity(article))
    }

    override suspend fun deleteArticles(vararg articles: Article) {
        val articleArray =
            ArticleDatabaseMapper.domainModelListToEntityList(articles.toList()).toTypedArray()
        newsDao.deleteArticles(*articleArray)
    }

    override fun viewSavedArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
            .map { ArticleDatabaseMapper.entityListToDomainModelList(it) }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}