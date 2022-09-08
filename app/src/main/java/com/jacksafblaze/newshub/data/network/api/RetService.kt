package com.jacksafblaze.newshub.data.network.api

import com.jacksafblaze.newshub.data.network.model.NewsNetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetService {
    @GET("v2/everything")
    suspend fun getSearchedArticles(@Query("apiKey") apiKey: String,
                                    @Query("q") query: String,
                                    @Query("pageSize")pageSize: Int,
                                    @Query("page")page: Int): Response<NewsNetworkResult>
}