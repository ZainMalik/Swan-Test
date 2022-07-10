package com.zain.swan.network

import com.zain.swan.data.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("list/1/")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): Movies

}