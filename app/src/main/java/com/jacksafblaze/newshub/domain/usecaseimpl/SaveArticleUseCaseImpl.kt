package com.jacksafblaze.newshub.domain.usecaseimpl

import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.SaveArticleUseCase

class SaveArticleUseCaseImpl(private val newsRepository: NewsRepository): SaveArticleUseCase {
    override suspend fun execute(article: Article) {
        newsRepository.saveArticle(article)
    }

}