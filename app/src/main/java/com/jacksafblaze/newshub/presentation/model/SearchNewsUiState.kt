package com.jacksafblaze.newshub.presentation.model

data class SearchNewsUiState(
    val query: String? = null,
    val userMessage: String? = null,
    val isLoading: Boolean = false
)
