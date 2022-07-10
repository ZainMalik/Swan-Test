package com.zain.swan.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.zain.swan.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {
    val movieItemsUiStates = movieRepository.getMovies()
        .map { pagingData ->
            pagingData.map { result -> MovieItemUiState(result) }
        }.cachedIn(viewModelScope)
}
