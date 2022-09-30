package com.jacksafblaze.newshub.data.network.api

import com.jacksafblaze.newshub.BuildConfig
import com.jacksafblaze.newshub.data.network.model.NewsNetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetService {
    @GET("v2/everything")
    suspend fun getSearchedArticles(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String = "relevancy"
    ): Response<NewsNetworkResult>

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("country") country: String = "us"
    ): Response<NewsNetworkResult>
}