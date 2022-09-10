package com.jacksafblaze.newshub.domain.usecaseimpl

import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.ViewSavedArticlesUseCase
import kotlinx.coroutines.flow.Flow

class ViewSavedArticlesUseCaseImpl(private val newsRepository: NewsRepository): ViewSavedArticlesUseCase {
    override fun execute(): Flow<List<Article>> {
        return newsRepository.viewSavedArticles()
    }

}