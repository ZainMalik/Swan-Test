package com.zain.swan.data.repository

import androidx.paging.PagingData
import com.zain.swan.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Result>>
}