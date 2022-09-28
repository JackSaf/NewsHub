package com.jacksafblaze.newshub.domain.usecaseimpl

import androidx.paging.PagingData
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.ViewTopHeadlinesUseCase
import kotlinx.coroutines.flow.Flow

class ViewTopHeadlinesUseCaseImpl(private val repository: NewsRepository) : ViewTopHeadlinesUseCase {
    override fun execute(): Flow<PagingData<Article>> {
        return repository.getTopHeadlines()
    }
}