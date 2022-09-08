package com.jacksafblaze.newshub.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jacksafblaze.newshub.data.network.api.RetService
import com.jacksafblaze.newshub.data.network.model.Article
import retrofit2.HttpException

class NewsPagingSource(private val retService: RetService, private val query: String): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageNumber = params.key ?: 1

        return try{
            val result = retService.getSearchedArticles("api_key", query, pageNumber, params.loadSize)
            val articles = result.body()?.articles!!
            val nextKey = if(articles.isEmpty() || articles.size < params.loadSize){
                null
            }
            else{
                pageNumber + 1
            }
            val prevKey = if(pageNumber == 1){
                null
            }
            else{
                pageNumber - 1
            }
            LoadResult.Page(data = articles, nextKey = nextKey, prevKey = prevKey)
        }
        catch(e: HttpException){
            return LoadResult.Error(e)
        }

    }

}