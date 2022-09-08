package com.jacksafblaze.newshub.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jacksafblaze.newshub.data.network.api.RetService
import com.jacksafblaze.newshub.data.network.networkmapper.ArticleNetworkEntityMapper
import com.jacksafblaze.newshub.data.network.paging.NewsPagingSource
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(val retService: RetService,
                         val articleNetworkEntityMapper: ArticleNetworkEntityMapper): NewsRepository {
    override fun getSearchedNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(retService, query) }
        ).flow.map { pagingData -> pagingData.map{ articleNetworkEntityMapper.entityToDomainModel(it) } }
    }
    companion object{
        const val NETWORK_PAGE_SIZE = 30
    }
}