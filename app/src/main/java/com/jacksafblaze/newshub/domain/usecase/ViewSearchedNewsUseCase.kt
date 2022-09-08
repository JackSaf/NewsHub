package com.jacksafblaze.newshub.domain.usecase

import androidx.paging.PagingData
import com.jacksafblaze.newshub.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ViewSearchedNewsUseCase{
    fun execute(query: String): Flow<PagingData<Article>>
}