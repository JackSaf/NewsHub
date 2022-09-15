package com.jacksafblaze.newshub.data.network.model


import com.google.gson.annotations.SerializedName

data class NewsNetworkResult(
    @SerializedName("articles")
    val articleNetworkEntities: List<ArticleNetworkEntity>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)