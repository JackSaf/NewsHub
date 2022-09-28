package com.jacksafblaze.newshub.domain.usecase

import androidx.paging.PagingData
import com.jacksafblaze.newshub.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ViewTopHeadlinesUseCase {
    fun execute(): Flow<PagingData<Article>>
}