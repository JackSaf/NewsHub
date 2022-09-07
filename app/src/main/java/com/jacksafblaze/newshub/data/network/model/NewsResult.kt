package com.jacksafblaze.newshub.data.network.model


import com.google.gson.annotations.SerializedName

data class NewsResult(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)