package com.jacksafblaze.newshub.domain.usecaseimpl

import androidx.paging.PagingData
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.ViewSearchedNewsUseCase
import kotlinx.coroutines.flow.Flow

class ViewSearchedNewsUseCaseImpl(private val newsRepository: NewsRepository): ViewSearchedNewsUseCase {
    override fun execute(query: String): Flow<PagingData<Article>> {
        return newsRepository.getSearchedNews(query)
    }
}