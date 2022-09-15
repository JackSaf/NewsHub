package com.jacksafblaze.newshub.presentation.model

import com.jacksafblaze.newshub.domain.model.Article

sealed class UiModel{
    data class NewsItem(val article: Article): UiModel()
    data class SeparatorItem(val description: String): UiModel()
}
