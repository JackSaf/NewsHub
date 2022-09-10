package com.jacksafblaze.newshub.domain.usecaseimpl

import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.DeleteSavedArticleUseCase

class DeleteSavedArticleUseCaseImpl(private val newsRepository: NewsRepository): DeleteSavedArticleUseCase {
    override suspend fun execute(vararg articles: Article) {
        newsRepository.deleteArticles(*articles)
    }
}