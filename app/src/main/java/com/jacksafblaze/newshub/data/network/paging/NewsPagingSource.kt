package com.jacksafblaze.newshub.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jacksafblaze.newshub.data.network.api.RetService
import com.jacksafblaze.newshub.data.network.model.Article
import retrofit2.HttpException

class NewsPagingSource(val retService: RetService, val query: String): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try{
            val nextPageNumber = params.key ?: 1
            val result = retService.getSearchedArticles("api_key", query)
            val body = result.body()
        }
        catch(e: HttpException){
            return LoadResult.Error(e)
        }

    }

}