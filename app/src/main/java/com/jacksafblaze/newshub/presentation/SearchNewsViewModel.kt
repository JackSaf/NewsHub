package com.jacksafblaze.newshub.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.usecase.DeleteSavedArticleUseCase
import com.jacksafblaze.newshub.domain.usecase.SaveArticleUseCase
import com.jacksafblaze.newshub.domain.usecase.ViewSearchedNewsUseCase
import com.jacksafblaze.newshub.presentation.model.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchNewsViewModel(
    private val deleteSavedArticleUseCase: DeleteSavedArticleUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val viewSearchedNewsUseCase: ViewSearchedNewsUseCase): ViewModel() {
    private val query = MutableStateFlow("")

    fun searchForNews(query: String): Flow<PagingData<UiModel>> {
        return viewSearchedNewsUseCase.execute(query).map { pagingData -> pagingData.map { UiModel.NewsItem(it) } }
    }
    fun saveArticle(uiModel: UiModel.NewsItem) = viewModelScope.launch{
        saveArticleUseCase.execute(uiModel.article)
    }
    fun deleteSavedArticle(uiModel: UiModel.NewsItem) = viewModelScope.launch {
        deleteSavedArticleUseCase.execute(uiModel.article)
    }
}