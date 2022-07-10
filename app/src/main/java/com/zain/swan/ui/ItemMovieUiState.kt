package com.zain.swan.ui

import com.zain.swan.common.BaseUiState
import com.zain.swan.data.model.Result
import com.zain.swan.util.Constants

data class MovieItemUiState(private val result: Result) : BaseUiState() {

    fun getImageUrl() = Constants.IMAGE_BASE_URL + result.poster_path

    fun getName() = result.original_title

    fun getOverview() = result.overview

    fun getDate() = result.release_date

}