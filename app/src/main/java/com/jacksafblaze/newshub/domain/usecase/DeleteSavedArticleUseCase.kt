package com.jacksafblaze.newshub.domain.usecase

import com.jacksafblaze.newshub.domain.model.Article

interface DeleteSavedArticleUseCase {
    suspend fun execute(vararg articles: Article)
}