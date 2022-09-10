package com.jacksafblaze.newshub.domain.usecase

import com.jacksafblaze.newshub.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ViewSavedArticlesUseCase {
    fun execute(): Flow<List<Article>>
}