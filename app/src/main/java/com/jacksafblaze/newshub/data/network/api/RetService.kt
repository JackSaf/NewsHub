package com.jacksafblaze.newshub.data.network.api

import com.jacksafblaze.newshub.data.network.model.NewsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetService {
    @GET("v2/everything")
    suspend fun getSearchedArticles(@Query("apiKey") apiKey: String,
                                    @Query("q") query: String,
                                    @Query("page")page: Int,
                                    @Query("pageSize")pageSize: Int): Response<NewsResult>
}