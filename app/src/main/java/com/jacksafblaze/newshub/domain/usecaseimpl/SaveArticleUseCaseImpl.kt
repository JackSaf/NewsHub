package com.jacksafblaze.newshub.domain.usecaseimpl

import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.SaveArticleUseCase

class SaveArticleUseCaseImpl(val newsRepository: NewsRepository): SaveArticleUseCase {
    override fun execute(article: Article) {
        TODO("Not yet implemented")
    }

}