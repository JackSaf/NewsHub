package com.jacksafblaze.newshub.domain.usecase

import com.jacksafblaze.newshub.domain.model.Article

interface SaveArticleUseCase {
    suspend fun execute(article: Article)
}