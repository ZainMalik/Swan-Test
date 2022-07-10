package com.zain.swan.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zain.swan.data.model.Result
import com.zain.swan.network.Service
import com.zain.swan.util.Constants

class MoviePagingDataSource(private val service: Service) :
    PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getMovies(page, Constants.API_KEY)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}
