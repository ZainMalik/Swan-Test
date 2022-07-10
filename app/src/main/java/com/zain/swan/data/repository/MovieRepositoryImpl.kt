package com.zain.swan.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zain.swan.data.model.Result
import com.zain.swan.data.pagingdatasource.MoviePagingDataSource
import com.zain.swan.network.Service
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val service: Service
) : MovieRepository {
    override fun getMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { MoviePagingDataSource(service) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
