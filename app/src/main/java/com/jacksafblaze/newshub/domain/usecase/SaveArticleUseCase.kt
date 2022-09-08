package com.jacksafblaze.newshub.domain.usecase

import com.jacksafblaze.newshub.domain.model.Article

interface SaveArticleUseCase {
    fun execute(article: Article)
}