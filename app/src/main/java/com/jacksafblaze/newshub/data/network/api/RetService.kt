package com.jacksafblaze.newshub.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RetService {
    @GET("v2/everything")
    fun getSearchedArticles(@Query("apiKey") apiKey: String, @Query("q") query: String)
}