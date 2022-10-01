package com.jacksafblaze.newshub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.domain.usecase.DeleteSavedArticleUseCase
import com.jacksafblaze.newshub.domain.usecase.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val saveArticleUseCase: SaveArticleUseCase,
    private val deleteSavedArticleUseCase: DeleteSavedArticleUseCase
) : ViewModel() {
    lateinit var article: Article
}